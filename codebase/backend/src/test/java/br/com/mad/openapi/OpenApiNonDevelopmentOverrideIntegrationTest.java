package br.com.mad.openapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(properties = {
    "springdoc.api-docs.enabled=true",
    "springdoc.swagger-ui.enabled=true"
})
@AutoConfigureMockMvc
@ActiveProfiles("production")
@Testcontainers
class OpenApiNonDevelopmentOverrideIntegrationTest {

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
    private MockMvc mockMvc;

    @Test
    void shouldHideOpenApiWhenEnabledOutsideDevelopmentProfile() throws Exception {
        MvcResult apiDocsResult = mockMvc.perform(get("/v3/api-docs")).andReturn();

        assertThat(apiDocsResult.getResponse().getStatus())
                .as("Non-development profile should hide the OpenAPI document")
                .isEqualTo(HttpStatus.NOT_FOUND.value());

        MvcResult swaggerUiResult = mockMvc.perform(get("/swagger-ui.html")).andReturn();

        assertThat(swaggerUiResult.getResponse().getStatus())
                .as("Non-development profile should hide Swagger UI")
                .isEqualTo(HttpStatus.NOT_FOUND.value());

        MvcResult authenticatedApiDocsResult = mockMvc
                .perform(get("/v3/api-docs").with(user("reviewer")))
                .andReturn();

        assertThat(authenticatedApiDocsResult.getResponse().getStatus())
                .as("Authenticated users should not discover the OpenAPI document outside development")
                .isEqualTo(HttpStatus.NOT_FOUND.value());

        MvcResult authenticatedSwaggerUiResult = mockMvc
                .perform(get("/swagger-ui.html").with(user("reviewer")))
                .andReturn();

        assertThat(authenticatedSwaggerUiResult.getResponse().getStatus())
                .as("Authenticated users should not discover Swagger UI outside development")
                .isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
