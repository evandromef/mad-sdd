package br.com.mad.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/system")
@Tag(name = "Sistema", description = "Endpoints tecnicos da API")
public class SystemStatusController {

    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Consultar estado da API")
    @ApiResponse(
            responseCode = "200",
            description = "API disponivel",
            content = @Content(schema = @Schema(implementation = SystemStatusResponse.class)))
    public SystemStatusResponse status() {
        return new SystemStatusResponse("UP");
    }
}
