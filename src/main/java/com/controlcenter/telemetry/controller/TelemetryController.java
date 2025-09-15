package com.controlcenter.telemetry.controller;

import com.controlcenter.telemetry.dto.TelemetryEnrollRequest;
import com.controlcenter.telemetry.dto.TelemetryEnrollResponse;
import com.controlcenter.telemetry.dto.TelemetryInventoryDTO;
import com.controlcenter.telemetry.dto.TelemetryMetricsDTO;
import com.controlcenter.telemetry.service.TelemetryService;
import com.controlcenter.telemetry.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
@Tag(name = "Telemetry", description = "Recepção de dados dos agentes (enroll, metrics, inventory)")
public class TelemetryController {

    private final TelemetryService service;

    @PostMapping("/enroll")
    @Operation(summary = "Registrar/atualizar agente por fingerprint")
    public ResponseEntity<TelemetryEnrollResponse> enroll(@RequestBody @Valid TelemetryEnrollRequest body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.enroll(body));
    }

    @PostMapping("/metrics")
    @Operation(summary = "Receber métricas detalhadas do agente")
    public ResponseEntity<Void> metrics(@RequestBody @Valid TelemetryMetricsRequest body) {
        service.saveMetrics(body);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/inventory")
    @Operation(summary = "Receber inventário completo do agente")
    public ResponseEntity<Void> inventory(@RequestBody @Valid TelemetryInventoryRequest body) {
        service.saveInventory(body);
        return ResponseEntity.ok().build();
    }
}
