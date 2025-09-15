package com.controlcenter.telemetry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.UUID;

public record TelemetryMetricsDTO(
        @NotNull UUID agentId,
        @PositiveOrZero @Schema(example = "35.2") Double cpuUsagePercent,
        @PositiveOrZero @Schema(example = "68.7") Double memoryUsagePercent,
        @PositiveOrZero @Schema(example = "82.0") Double diskUsagePercent,
        @NotNull @Schema(example = "2025-09-01T12:00:00Z") Instant timestamp
) {}