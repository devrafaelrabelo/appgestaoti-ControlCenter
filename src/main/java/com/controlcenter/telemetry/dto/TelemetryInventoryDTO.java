package com.controlcenter.telemetry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record TelemetryInventoryDTO(
        @NotNull UUID agentId,
        @Schema(example = "Intel Core i7-9700") String cpu,
        @Schema(example = "16 GB DDR4") String ram,
        List<String> disks,
        List<String> software,
        @Schema(example = "NVIDIA GTX 1650") String gpu,
        @Schema(example = "Dell Inc.") String manufacturer,
        @Schema(example = "LATITUDE-5420") String model,
        @Schema(example = "SN-XYZ-123") String serialNumber,
        @Schema(example = "2025-09-01T12:00:00Z") Instant capturedAt
) {}