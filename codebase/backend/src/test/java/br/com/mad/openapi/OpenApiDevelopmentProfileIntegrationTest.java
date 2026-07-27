package br.com.mad.openapi;

import static org.assertj.core.api.Assertions.assertThat;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("development")
@Testcontainers
class OpenApiDevelopmentProfileIntegrationTest {

    private static final String SYSTEM_STATUS_PATH = "/api/v1/system/status";

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

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldExposeDocumentedApiOnlyInDevelopmentProfile() throws Exception {
        MvcResult result = mockMvc.perform(get("/v3/api-docs")).andReturn();

        int actualHttpStatus = result.getResponse().getStatus();

        assertThat(actualHttpStatus)
                .as("Development profile should expose the OpenAPI document")
                .isEqualTo(HttpStatus.OK.value());

        MvcResult swaggerUiResult = mockMvc.perform(get("/swagger-ui.html")).andReturn();

        assertThat(swaggerUiResult.getResponse().getStatus())
                .as("Development profile should expose Swagger UI")
                .isEqualTo(HttpStatus.FOUND.value());

        JsonNode openApiDocument = objectMapper.readTree(result.getResponse().getContentAsString());

        assertThat(openApiDocument.path("openapi").asText())
                .as("Should generate an OpenAPI 3.0 document")
                .startsWith("3.0");

        assertThat(openApiDocument.path("info").path("title").asText())
                .as("Should identify the MAD API")
                .isEqualTo("MAD API");

        assertThat(openApiDocument.path("paths").fieldNames())
                .as("Should document only the existing versioned API path")
                .toIterable()
                .containsExactly(SYSTEM_STATUS_PATH);

        JsonNode statusOperation = openApiDocument.path("paths").path(SYSTEM_STATUS_PATH).path("get");

        assertThat(statusOperation.isMissingNode())
                .as("Should document GET for the system status")
                .isFalse();

        assertThat(statusOperation.path("responses").has("200"))
                .as("Should document the successful system status response")
                .isTrue();

        String responseSchemaReference = statusOperation
                .path("responses")
                .path("200")
                .path("content")
                .path("application/json")
                .path("schema")
                .path("$ref")
                .asText();

        assertThat(responseSchemaReference)
                .as("Should use the typed system status response schema")
                .isEqualTo("#/components/schemas/SystemStatusResponse");
    }
}
