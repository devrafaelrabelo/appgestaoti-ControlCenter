package com.controlcenter.telemetry.dto;

import java.util.UUID;

public record TelemetryEnrollResponse(
        UUID agentId,
        String status
) {}