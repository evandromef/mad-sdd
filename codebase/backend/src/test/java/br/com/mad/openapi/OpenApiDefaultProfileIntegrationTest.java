package br.com.mad.openapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class OpenApiDefaultProfileIntegrationTest {

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
    void shouldNotExposeOpenApiOutsideDevelopmentProfile() throws Exception {
        MvcResult apiDocsResult = mockMvc.perform(get("/v3/api-docs")).andReturn();
        MvcResult swaggerUiResult = mockMvc.perform(get("/swagger-ui.html")).andReturn();

        assertThat(apiDocsResult.getResponse().getStatus())
                .as("Default profile should require authentication for the OpenAPI document path")
                .isEqualTo(HttpStatus.UNAUTHORIZED.value());

        assertThat(swaggerUiResult.getResponse().getStatus())
                .as("Default profile should require authentication for the Swagger UI path")
                .isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
