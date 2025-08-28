package com.controlcenter.infra.job;

import com.controlcenter.core.job.JobStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryJobStore implements JobStore {
    private static final Logger log = LoggerFactory.getLogger(InMemoryJobStore.class);
    private final Map<UUID, Map<String, Object>> store = new ConcurrentHashMap<>();

    @Override
    public UUID start(String jobType, String idempotencyKey, UUID requestedBy) {
        UUID jobId = UUID.randomUUID();
        store.put(jobId, new ConcurrentHashMap<>());
        log.info("[JobStore] START jobId={} type={} idemKey={} requestedBy={}",
                jobId, jobType, idempotencyKey, requestedBy);
        return jobId;
    }

    @Override
    public void step(UUID jobId, String step, String status, Map<String, ?> details) {
        var map = store.computeIfAbsent(jobId, k -> new ConcurrentHashMap<>());
        var payload = new ConcurrentHashMap<String, Object>();
        payload.put("status", status);
        if (details != null) payload.putAll(details);
        map.put(step, payload);
        log.info("[JobStore] STEP jobId={} step={} status={} details={}", jobId, step, status, details);
    }

    @Override
    public void finish(UUID jobId, String status, String error) {
        var map = store.computeIfAbsent(jobId, k -> new ConcurrentHashMap<>());
        map.put("_final", Map.of("status", status, "error", error));
        log.info("[JobStore] FINISH jobId={} status={} error={}", jobId, status, error);
    }
}
