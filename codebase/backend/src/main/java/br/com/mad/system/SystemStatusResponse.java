package br.com.mad.system;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estado operacional da API")
public record SystemStatusResponse(
        @Schema(
                        description = "Estado atual da API",
                        example = "UP",
                        allowableValues = "UP",
                        requiredMode = Schema.RequiredMode.REQUIRED)
                String status) {}
