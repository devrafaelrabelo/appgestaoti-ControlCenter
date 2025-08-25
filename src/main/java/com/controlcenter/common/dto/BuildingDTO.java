package com.controlcenter.common.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BuildingDTO(
        UUID id,
        String name,
        String code,
        String description,
        AddressDTO address,
        boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}