CREATE SCHEMA IF NOT EXISTS telemetry;
SET search_path TO telemetry;

CREATE TABLE telemetry_inventory (
  id UUID PRIMARY KEY,
  device_id VARCHAR(255) NOT NULL,
  payload TEXT NOT NULL,
  captured_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_inventory_device_time
  ON telemetry_inventory(device_id, captured_at);