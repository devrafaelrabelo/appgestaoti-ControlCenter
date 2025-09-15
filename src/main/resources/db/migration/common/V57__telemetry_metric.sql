CREATE SCHEMA IF NOT EXISTS telemetry;
SET search_path TO telemetry;

CREATE TABLE telemetry_metrics (
  id UUID PRIMARY KEY,
  device_id VARCHAR(255) NOT NULL,
  agent_name VARCHAR(100) NOT NULL,
  agent_version VARCHAR(50) NOT NULL,
  timestamp TIMESTAMP NOT NULL,
  cpu_percent DOUBLE PRECISION,
  process_count INT,
  uptime_seconds BIGINT,
  payload TEXT,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_metrics_device_time
  ON telemetry_metrics(device_id, timestamp);

-- índice textual para buscas rápidas no campo payload
CREATE INDEX idx_metrics_payload_tsv
  ON telemetry_metrics
  USING GIN (to_tsvector('simple', payload));