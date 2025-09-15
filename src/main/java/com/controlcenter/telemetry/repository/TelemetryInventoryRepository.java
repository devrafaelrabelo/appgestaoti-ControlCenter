package com.controlcenter.telemetry.repository;

import com.controlcenter.telemetry.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface TelemetryInventoryRepository extends JpaRepository<TelemetryInventory, UUID> {}

