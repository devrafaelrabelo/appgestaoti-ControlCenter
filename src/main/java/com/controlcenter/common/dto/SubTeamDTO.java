package com.controlcenter.common.dto;

import java.util.UUID;

public record SubTeamDTO(
        UUID id,
        String name,
        String description,
        UUID teamId,
        UUID managerId
) {}
