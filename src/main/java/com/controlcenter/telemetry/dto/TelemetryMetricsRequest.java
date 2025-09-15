package com.controlcenter.telemetry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TelemetryMetricsRequest(
        @NotNull String device_id,
        @NotNull Agent agent,
        @NotNull Metrics metrics
) {
    public record Agent(String name, String version) {}

    public record Metrics(
            long timestamp,
            double cpu_percent,
            Memory memory,
            DiskRoot disk_root,
            List<Disk> disks,
            int process_count,
            long uptime_seconds,
            Sessions sessions // novo campo
    ) {}

    public record Memory(long total, long available, long used, double percent) {}
    public record DiskRoot(long total, long used, long free, double percent) {}
    public record Disk(String device, String fstype, String mountpoint, long total, long used, long free, double percent) {}

    public record Sessions(
            String active_user,
            List<UserSession> users
    ) {}

    public record UserSession(
            String name,
            int session_id,
            String state,
            boolean is_active,
            String station,
            String client,
            String source
    ) {}
}
