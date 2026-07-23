package br.com.mad.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class DatabaseMigrationIntegrationTest {

    private static final String APPLICATION_SCHEMA = "mad_db";
    private static final String PUBLIC_SCHEMA = "public";
    private static final String FLYWAY_HISTORY_TABLE = "flyway_schema_history";
    private static final String INITIAL_MIGRATION_VERSION = "1";

    @Container
    static final PostgreSQLContainer<?> POSTGRESQL = new PostgreSQLContainer<>("postgres:17-alpine");

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("MAD_DB_HOST", POSTGRESQL::getHost);
        registry.add("MAD_DB_PORT", POSTGRESQL::getFirstMappedPort);
        registry.add("MAD_DB_NAME", POSTGRESQL::getDatabaseName);
        registry.add("MAD_DB_USERNAME", POSTGRESQL::getUsername);
        registry.add("MAD_DB_PASSWORD", POSTGRESQL::getPassword);
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    @Autowired
    private JpaProperties jpaProperties;

    @Test
    void shouldConnectUsingExternalDatabaseProperties() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            String actualJdbcUrl = connection.getMetaData().getURL();

            assertThat(actualJdbcUrl)
                    .as("Should connect to PostgreSQL container via external properties")
                    .isEqualTo(expectedJdbcUrl());

            String authenticatedDatabaseUser = connection.getMetaData().getUserName();

            assertThat(authenticatedDatabaseUser)
                    .as("Should authenticate using MAD_DB_USERNAME")
                    .isEqualTo(POSTGRESQL.getUsername());
        }
    }

    @Test
    void shouldConfigureFlywayAndHibernateWithApplicationSchema() {
        String flywayDefaultSchema = flyway.getConfiguration().getDefaultSchema();

        assertThat(flywayDefaultSchema)
                .as("Flyway should use the application schema by default")
                .isEqualTo(APPLICATION_SCHEMA);

        String[] flywayManagedSchemas = flyway.getConfiguration().getSchemas();

        assertThat(flywayManagedSchemas)
                .as("Flyway should manage only the application schema")
                .containsExactly(APPLICATION_SCHEMA);

        String hibernateDefaultSchema = jpaProperties.getProperties().get("hibernate.default_schema");

        assertThat(hibernateDefaultSchema)
                .as("Hibernate should use the application schema by default")
                .isEqualTo(APPLICATION_SCHEMA);
    }

    @Test
    void shouldApplyInitialMigrationOnlyOnce() throws Exception {
        String currentMigrationVersion = flyway.info().current().getVersion().getVersion();

        assertThat(currentMigrationVersion)
                .as("Should apply the initial Flyway migration")
                .isEqualTo(INITIAL_MIGRATION_VERSION);

        boolean applicationSchemaExists = schemaExists(APPLICATION_SCHEMA);

        assertThat(applicationSchemaExists)
                .as("Should create the application schema")
                .isTrue();

        boolean applicationSchemaHasFlywayHistory = tableExists(APPLICATION_SCHEMA, FLYWAY_HISTORY_TABLE);

        assertThat(applicationSchemaHasFlywayHistory)
                .as("Should store Flyway history in the application schema")
                .isTrue();

        boolean publicSchemaHasFlywayHistory = tableExists(PUBLIC_SCHEMA, FLYWAY_HISTORY_TABLE);

        assertThat(publicSchemaHasFlywayHistory)
                .as("Should not store Flyway history in the public schema")
                .isFalse();

        int migrationsExecutedOnSecondRun = flyway.migrate().migrationsExecuted;

        assertThat(migrationsExecutedOnSecondRun)
                .as("Should not reapply migrations on a second Flyway run")
                .isZero();
    }

    private String expectedJdbcUrl() {
        return "jdbc:postgresql://%s:%d/%s"
                .formatted(POSTGRESQL.getHost(), POSTGRESQL.getFirstMappedPort(), POSTGRESQL.getDatabaseName());
    }

    private boolean schemaExists(String schema) throws Exception {
        try (Connection connection = dataSource.getConnection();
                ResultSet schemas = connection.getMetaData().getSchemas(null, schema)) {
            return schemas.next();
        }
    }

    private boolean tableExists(String schema, String table) throws Exception {
        try (Connection connection = dataSource.getConnection();
                ResultSet tables = connection.getMetaData().getTables(null, schema, table, new String[] {"TABLE"})) {
            return tables.next();
        }
    }
}
