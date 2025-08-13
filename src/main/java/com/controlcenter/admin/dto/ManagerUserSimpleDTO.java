package com.controlcenter.admin.dto;

import java.util.UUID;

public record ManagerUserSimpleDTO(
        UUID id,
        String fullName
) {}