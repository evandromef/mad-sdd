package br.com.mad.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class DatabaseMigrationIntegrationTest {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer<?> POSTGRESQL = new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    @Autowired
    private JpaProperties jpaProperties;

    @Test
    void shouldApplyInitialMigrationToCleanDatabase() throws Exception {
        assertThat(flyway.info().current().getVersion().getVersion()).isEqualTo("1");
        assertThat(flyway.getConfiguration().getDefaultSchema()).isEqualTo("mad_db");
        assertThat(flyway.getConfiguration().getSchemas()).containsExactly("mad_db");
        assertThat(flyway.migrate().migrationsExecuted).isZero();
        assertThat(jpaProperties.getProperties()).containsEntry("hibernate.default_schema", "mad_db");

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT EXISTS (SELECT 1 FROM information_schema.schemata WHERE schema_name = 'mad_db'), "
                                + "to_regclass('mad_db.flyway_schema_history') IS NOT NULL, "
                                + "to_regclass('public.flyway_schema_history') IS NULL")) {
            assertThat(resultSet.next()).isTrue();
            assertThat(resultSet.getBoolean(1)).isTrue();
            assertThat(resultSet.getBoolean(2)).isTrue();
            assertThat(resultSet.getBoolean(3)).isTrue();
        }
    }
}
