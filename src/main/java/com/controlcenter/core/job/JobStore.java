package com.controlcenter.core.job;

import java.util.Map;
import java.util.UUID;

public interface JobStore {
    UUID start(String jobType, String idempotencyKey, UUID requestedBy);
    void step(UUID jobId, String step, String status, Map<String, ?> details);
    void finish(UUID jobId, String status, String error);
}
