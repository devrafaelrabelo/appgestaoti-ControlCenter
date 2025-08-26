package com.controlcenter.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(name = "BuildingCompanyLinkBody")
public record BuildingCompanyLinkBody(
        @NotNull @Schema(example = "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee") UUID buildingId,
        @NotNull @Schema(example = "11111111-2222-3333-4444-555555555555") UUID companyId
) {}
