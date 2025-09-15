package com.controlcenter.entity.resource;

import com.controlcenter.entity.common.Company;
import com.controlcenter.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "resources", schema = "resource")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @NotNull
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ResourceStatus status;

    private String description;

    // === Origem ===
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceOrigin origin;

    // Se for OWNED → obrigatório
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    // Se for LEASED → obrigatório
    private String lessorCompanyName;

    // === Dados patrimoniais ===
    @NotBlank
    @Column(nullable = false)
    private String assetTag;

    @NotBlank
    @Column(nullable = false)
    private String serialNumber;

    @NotBlank
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    private LocalDate purchaseDate;

    private LocalDate warrantyEndDate;

    // === Localização e setor atual ===
    private String location;
    private String responsibleSector;

    // === Relacionamentos com outras entidades ===
    @ManyToOne
    @JoinColumn(name = "current_user_id")
    private User currentUser;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "resource_type_id", nullable = false)
    private ResourceType resourceType;

    @Builder.Default
    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AllocationHistory> allocationHistory = new ArrayList<>();

    // === Flags e status lógicos ===
    @Builder.Default
    @Column(nullable = false)
    private boolean availableForUse = true;

    // === Auditoria ===
    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // === Regras de validação condicional ===
    @AssertTrue(message = "Company é obrigatório quando o recurso é OWNED")
    public boolean isCompanyValidForOwned() {
        return origin != ResourceOrigin.OWNED || company != null;
    }

    @AssertTrue(message = "lessorCompanyName é obrigatório quando o recurso é LEASED")
    public boolean isLessorValidForLeased() {
        return origin != ResourceOrigin.LEASED ||
                (lessorCompanyName != null && !lessorCompanyName.isBlank());
    }
}
