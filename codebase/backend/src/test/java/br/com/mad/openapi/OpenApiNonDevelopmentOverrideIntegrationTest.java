package br.com.mad.openapi;

import static org.assertj.core.api.Assertions.assertThat;
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
    void shouldRequireAuthenticationWhenOpenApiIsEnabledOutsideDevelopmentProfile() throws Exception {
        MvcResult apiDocsResult = mockMvc.perform(get("/v3/api-docs")).andReturn();
        MvcResult swaggerUiResult = mockMvc.perform(get("/swagger-ui.html")).andReturn();

        assertThat(apiDocsResult.getResponse().getStatus())
                .as("Non-development profile should protect the OpenAPI document")
                .isEqualTo(HttpStatus.UNAUTHORIZED.value());

        assertThat(swaggerUiResult.getResponse().getStatus())
                .as("Non-development profile should protect Swagger UI")
                .isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
