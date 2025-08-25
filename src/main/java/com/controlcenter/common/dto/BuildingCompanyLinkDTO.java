package com.controlcenter.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Vincular/Desvincular Company a um Building")
public record BuildingCompanyLinkDTO(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        UUID companyId
) { }