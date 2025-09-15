package com.controlcenter.telemetry.repository;

import com.controlcenter.telemetry.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;


public interface TelemetryAgentRepository extends JpaRepository<TelemetryAgent, UUID> {
    Optional<TelemetryAgent> findByHostnameAndMachine(String hostname, String machine);
}
