package com.controlcenter.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "DTO de criação/edição de prédio/local")
public record BuildingUpsertDTO(
        @Schema(example = "Matriz Sete Lagoas") String name,
        @Schema(example = "MAT-SL") String code,
        @Schema(example = "Prédio principal") String description,
        @Schema(example = "f1a7d3f2-5b61-4a0b-9a21-1f5b7f2c4d2f", description = "ID do endereço (opcional)") UUID addressId,
        @Schema(example = "true") Boolean active
) {}