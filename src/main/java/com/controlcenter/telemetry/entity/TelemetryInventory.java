package com.controlcenter.telemetry.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "telemetry_inventory")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TelemetryInventory {
    @Id @UuidGenerator
    private UUID id;

    @Column(nullable = false) private String deviceId;

    @Column(name = "payload", columnDefinition = "TEXT")
    private String payload;

    @Column(nullable = false) private Instant capturedAt;
}
