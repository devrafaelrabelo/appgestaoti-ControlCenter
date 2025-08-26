package com.controlcenter.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "DTO de criação/edição de prédio/local")
public record BuildingUpsertDTO(
        @Schema(description = "ID do prédio (obrigatório no update)", example = "f9c9c1c1-8a7b-4a2b-9f9f-1234567890ab")
        UUID id,

        @Schema(example = "Matriz Sete Lagoas")
        String name,

        @Schema(example = "MAT-SL")
        String code,

        @Schema(example = "Prédio principal")
        String description,

        @Schema(description = "Endereço completo do prédio")
        AddressDTO address,

        @Schema(example = "true")
        Boolean active
) {}