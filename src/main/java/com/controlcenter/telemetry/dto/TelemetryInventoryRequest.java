package com.controlcenter.telemetry.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TelemetryInventoryRequest(
        @NotNull String device_id,
        @NotNull Inventory inventory
) {
    public record Inventory(
            OS os,
            Hardware hardware,
            Cpu cpu,
            Memory memory,
            List<Disk> disks,
            List<Network> network,
            List<Software> software
    ) {}

    public record OS(String name, String version, String build) {}

    public record Hardware(String manufacturer, String model, String serial) {}

    public record Cpu(
            String name,
            int cores,
            int threads,
            int max_clock_mhz
    ) {}

    public record Memory(long total_bytes) {}

    public record Disk(String model, String serial, long size_bytes) {}

    public record Network(
            String name,
            String mac,
            List<String> ipv4,
            List<String> ipv6
    ) {}

    public record Software(String name, String version) {}
}
