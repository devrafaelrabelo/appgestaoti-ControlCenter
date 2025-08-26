package com.controlcenter.entity.common;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "building", schema = "common")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 50, unique = true)
    private String code;

    @Column(columnDefinition = "text")
    private String description;

    // <<< Embutido (não é entidade, não tem FK)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",       column = @Column(name = "address_street", length = 255)),
            @AttributeOverride(name = "number",       column = @Column(name = "address_number", length = 50)),
            @AttributeOverride(name = "complement",   column = @Column(name = "address_complement", length = 255)),
            @AttributeOverride(name = "neighborhood", column = @Column(name = "address_neighborhood", length = 120)),
            @AttributeOverride(name = "city",         column = @Column(name = "address_city", length = 120)),
            @AttributeOverride(name = "state",        column = @Column(name = "address_state", length = 2)),
            @AttributeOverride(name = "country",      column = @Column(name = "address_country", length = 120)),
            @AttributeOverride(name = "postalCode",   column = @Column(name = "address_postal_code", length = 20))
    })
    private Address address;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        final OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
