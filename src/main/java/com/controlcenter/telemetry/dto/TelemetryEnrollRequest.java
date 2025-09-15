package com.controlcenter.telemetry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record TelemetryEnrollRequest(
        @NotNull Fingerprint fingerprint,
        @NotNull Labels labels
) {
    public record Fingerprint(
            @Schema(example = "DESKTOP-ABC") String hostname,
            @Schema(example = "Windows") String system,
            @Schema(example = "11") String release,
            @Schema(example = "10.0.22631") String version,
            @Schema(example = "AMD64") String machine,
            @Schema(example = "11th Gen Intel(R) Core(TM) i7-1185G7 @ 3.00GHz") String processor,
            @Schema(example = "3.11.9") String python_version
    ) {}
    public record Labels(
            @Schema(example = "dev") String env,
            @Schema(example = "sp-office") String site
    ) {}
}