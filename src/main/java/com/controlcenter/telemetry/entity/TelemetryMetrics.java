package com.controlcenter.telemetry.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.Instant;
import java.util.UUID;


@Entity @Table(name = "telemetry_metrics", schema = "telemetry",
        indexes = {@Index(name="idx_metric_agent_time", columnList = "agent_id,timestamp")})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TelemetryMetrics {
    @Id @UuidGenerator
    private UUID id;

    @Column(nullable = false) private String deviceId;
    @Column(nullable = false) private String agentName;
    @Column(nullable = false) private String agentVersion;
    @Column(nullable = false) private Instant timestamp;
    private double cpuPercent;
    private int processCount;
    private long uptimeSeconds;

    @Column(name = "payload", columnDefinition = "TEXT")
    private String payload;

    @Column(nullable = false) private Instant createdAt;
}
