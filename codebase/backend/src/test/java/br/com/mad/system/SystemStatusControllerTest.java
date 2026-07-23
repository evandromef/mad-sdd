package br.com.mad.system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import br.com.mad.config.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(SystemStatusController.class)
@Import(SecurityConfig.class)
class SystemStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkWithUpStatus() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/system/status")).andReturn();

        int actualHttpStatus = result.getResponse().getStatus();

        assertThat(actualHttpStatus)
                .as("Should return HTTP 200")
                .isEqualTo(HttpStatus.OK.value());

        MediaType actualContentType = MediaType.parseMediaType(result.getResponse().getContentType());
        boolean responseHasJsonContentType = actualContentType.isCompatibleWith(MediaType.APPLICATION_JSON);

        assertThat(responseHasJsonContentType)
                .as("Should return a JSON response")
                .isTrue();

        String actualSystemStatus = objectMapper
                .readTree(result.getResponse().getContentAsString())
                .path("status")
                .asText();

        assertThat(actualSystemStatus)
                .as("Should report the system as UP")
                .isEqualTo("UP");
    }
}
