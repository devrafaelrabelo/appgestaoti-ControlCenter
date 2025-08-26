package com.controlcenter.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(name = "IdBody", description = "Wrapper para operações que exigem ID no corpo")
public record IdBody(
        @NotNull @Schema(example = "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee")
        UUID id
) {}
