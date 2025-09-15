package com.controlcenter.telemetry.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "telemetry_agent",
        uniqueConstraints = @UniqueConstraint(name="ux_host_machine", columnNames = {"hostname","machine"})
)
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class TelemetryAgent {
    @Id @UuidGenerator private UUID id;

    @Column(nullable=false) private String hostname;
    @Column(nullable=false) private String machine;

    @Column(nullable=false) private String system;
    private String release;
    private String version;
    @Column(length=512) private String processor;
    private String pythonVersion;

    private String env;
    private String site;

    @Column(nullable=false) private Instant enrolledAt;
    @Column(nullable=false) private Instant updatedAt;
}
