package com.controlcenter.resource.dto;

import com.controlcenter.entity.resource.ResourceOrigin;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ResourceDTO {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private UUID statusId;   // referência ao ResourceStatus

    private String description;

    @NotNull
    private ResourceOrigin origin;

    // Se OWNED → obrigatório
    private UUID companyId;

    // Se LEASED → obrigatório
    private String lessorCompanyName;

    @NotBlank
    private String assetTag;

    @NotBlank
    private String serialNumber;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private LocalDate purchaseDate;

    private LocalDate warrantyEndDate;

    private String location;
    private String responsibleSector;

    private UUID currentUserId;   // referência ao usuário

    @NotNull
    private UUID resourceTypeId;  // referência ao tipo de recurso

    // === Regras de validação condicional ===
    @AssertTrue(message = "companyId é obrigatório quando o recurso é OWNED")
    public boolean isCompanyValidForOwned() {
        return origin != ResourceOrigin.OWNED || companyId != null;
    }

    @AssertTrue(message = "lessorCompanyName é obrigatório quando o recurso é LEASED")
    public boolean isLessorValidForLeased() {
        return origin != ResourceOrigin.LEASED ||
                (lessorCompanyName != null && !lessorCompanyName.isBlank());
    }
}
