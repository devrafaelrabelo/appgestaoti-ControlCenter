CREATE SCHEMA IF NOT EXISTS telemetry;
SET search_path TO telemetry;

CREATE TABLE telemetry_agent (
  id UUID PRIMARY KEY,
  hostname VARCHAR(255) NOT NULL,
  machine  VARCHAR(255) NOT NULL,
  system   VARCHAR(100) NOT NULL,
  release  VARCHAR(50),
  version  VARCHAR(100),
  processor VARCHAR(512),
  python_version VARCHAR(20),
  env VARCHAR(50),
  site VARCHAR(100),
  enrolled_at TIMESTAMP NOT NULL,
  updated_at  TIMESTAMP NOT NULL
);

ALTER TABLE telemetry_agent
  ADD CONSTRAINT ux_host_machine UNIQUE (hostname, machine);
