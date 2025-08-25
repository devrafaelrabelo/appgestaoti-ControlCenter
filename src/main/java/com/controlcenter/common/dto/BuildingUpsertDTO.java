package com.controlcenter.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de criação/edição de prédio/local")
public record BuildingUpsertDTO(
        @Schema(example = "Matriz Sete Lagoas") String name,
        @Schema(example = "MAT-SL") String code,
        @Schema(example = "Prédio principal") String description,
        @Schema(description = "Endereço completo do prédio") AddressDTO address,
        @Schema(example = "true") Boolean active
) {}