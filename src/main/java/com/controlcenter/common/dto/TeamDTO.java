package com.controlcenter.common.dto;

import java.util.UUID;

public record TeamDTO(
        UUID id,
        String name,
        String description,
        String location,
        UUID supervisorId
) {}
