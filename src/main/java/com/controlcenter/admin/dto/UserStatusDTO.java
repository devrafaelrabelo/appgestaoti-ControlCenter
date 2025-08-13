package com.controlcenter.admin.dto;

import java.util.UUID;

public record UserStatusDTO(
        UUID id,
        String name,
        String description,
        boolean active
) {}