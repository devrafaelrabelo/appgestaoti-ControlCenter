package com.controlcenter.admin.controller;

import com.controlcenter.core.dto.ProvisioningRequest;
import com.controlcenter.core.orchestrator.ProvisioningOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/provisioning")
@RequiredArgsConstructor
public class AdminProvisioningController {

    private final ProvisioningOrchestrator orchestrator;

    @PostMapping("/execute")
    @PreAuthorize("hasAuthority('bulk:execute') or hasAuthority('user:create')")
    public ResponseEntity<ProvisioningOrchestrator.JobResult> execute(
            @RequestHeader(value = "Idempotency-Key", required = false) String idemKey,
            @RequestBody ProvisioningRequest req) {

        String key = Optional.ofNullable(idemKey).orElse(UUID.randomUUID().toString());
        var result = orchestrator.provisionUser(req, key);
        return ResponseEntity.ok(result);
    }
}
