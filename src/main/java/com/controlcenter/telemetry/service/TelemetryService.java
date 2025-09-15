package com.controlcenter.telemetry.service;

import com.controlcenter.telemetry.dto.*;
import com.controlcenter.telemetry.entity.*;
import com.controlcenter.telemetry.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelemetryService {

    private final TelemetryAgentRepository telemetryAgentRepository;
    private final TelemetryMetricRepository telemetryMetricRepository;
    private final TelemetryInventoryRepository telemetryInventoryRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public TelemetryEnrollResponse enroll(TelemetryEnrollRequest r) {
        var f = r.fingerprint();
        var labels = r.labels();

        var agent = telemetryAgentRepository.findByHostnameAndMachine(f.hostname(), f.machine())
                .map(a -> {
                    a.setSystem(f.system());
                    a.setRelease(f.release());
                    a.setVersion(f.version());
                    a.setProcessor(f.processor());
                    a.setPythonVersion(f.python_version());
                    a.setEnv(labels.env());
                    a.setSite(labels.site());
                    a.setUpdatedAt(Instant.now());
                    return a;
                })
                .orElseGet(() -> TelemetryAgent.builder()
                        .id(UUID.randomUUID())
                        .hostname(f.hostname())
                        .machine(f.machine())
                        .system(f.system())
                        .release(f.release())
                        .version(f.version())
                        .processor(f.processor())
                        .pythonVersion(f.python_version())
                        .env(labels.env())
                        .site(labels.site())
                        .enrolledAt(Instant.now())
                        .updatedAt(Instant.now())
                        .build());

        telemetryAgentRepository.save(agent);
        return new TelemetryEnrollResponse(agent.getId(), "ENROLLED");
    }

    @Transactional
    public void saveMetrics(TelemetryMetricsRequest req) {
        try {
            var entity = TelemetryMetrics.builder()
                    .id(UUID.randomUUID())
                    .deviceId(req.device_id())
                    .agentName(req.agent().name())
                    .agentVersion(req.agent().version())
                    .timestamp(Instant.ofEpochSecond(req.metrics().timestamp()))
                    .cpuPercent(req.metrics().cpu_percent())
                    .processCount(req.metrics().process_count())
                    .uptimeSeconds(req.metrics().uptime_seconds())
                    .payload(objectMapper.writeValueAsString(req)) // guarda payload inteiro como JSON
                    .createdAt(Instant.now())
                    .build();
            telemetryMetricRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar métricas", e);
        }
    }

    @Transactional
    public void saveInventory(TelemetryInventoryRequest req) {
        try {
            var entity = TelemetryInventory.builder()
                    .id(UUID.randomUUID())
                    .deviceId(req.device_id())
                    .payload(objectMapper.writeValueAsString(req)) // salva payload completo
                    .capturedAt(Instant.now())
                    .build();
            telemetryInventoryRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar inventário", e);
        }
    }

    private double nvl(Double d) { return d == null ? 0.0 : d; }
}