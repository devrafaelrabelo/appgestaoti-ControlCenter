-- V55__create_building_and_company.sql
CREATE SCHEMA IF NOT EXISTS common;
SET search_path TO common;

-- ===========================
-- TABELA: BUILDING (Address embutido)
-- ===========================
CREATE TABLE IF NOT EXISTS common.building (
  id UUID PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  code VARCHAR(50) UNIQUE,
  description TEXT,

  -- Address embutido (NOMES DEVEM BATER COM @AttributeOverrides)
  address_street        VARCHAR(255),
  address_number        VARCHAR(50),
  address_complement    VARCHAR(255),
  address_neighborhood  VARCHAR(120),
  address_city          VARCHAR(120),
  address_state         VARCHAR(2),
  address_country       VARCHAR(120),
  address_postal_code   VARCHAR(20),

  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE common.building IS 'Cadastro de prédios/locais físicos da organização.';
COMMENT ON COLUMN common.building.code IS 'Código interno único do prédio/local.';

CREATE INDEX IF NOT EXISTS idx_building_name   ON common.building (name);
CREATE INDEX IF NOT EXISTS idx_building_active ON common.building (active);

-- Trigger para updated_at
CREATE OR REPLACE FUNCTION common.trg_building_set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at := NOW();
  RETURN NEW;
END; $$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_building_set_updated_at ON common.building;
CREATE TRIGGER trg_building_set_updated_at
BEFORE UPDATE ON common.building
FOR EACH ROW
EXECUTE FUNCTION common.trg_building_set_updated_at();

-- ===========================
-- TABELA: BUILDING_COMPANY (N:N)
-- ===========================
CREATE TABLE IF NOT EXISTS common.building_company (
  id UUID PRIMARY KEY,
  building_id UUID NOT NULL,
  company_id  UUID NOT NULL,
  created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  CONSTRAINT uk_building_company UNIQUE (building_id, company_id)
);

CREATE INDEX IF NOT EXISTS idx_building_company_building ON common.building_company (building_id);
CREATE INDEX IF NOT EXISTS idx_building_company_company  ON common.building_company (company_id);

-- FKs (condicionais, para não travar ordem)
DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema='common' AND table_name='building')
  AND NOT EXISTS (
    SELECT 1
    FROM pg_constraint c
    JOIN pg_class t ON t.oid = c.conrelid
    JOIN pg_namespace n ON n.oid = t.relnamespace
    WHERE c.conname = 'fk_building_company__building'
      AND n.nspname = 'common'
      AND t.relname = 'building_company'
  )
  THEN
    ALTER TABLE common.building_company
      ADD CONSTRAINT fk_building_company__building
      FOREIGN KEY (building_id) REFERENCES common.building(id) ON DELETE CASCADE;
  END IF;

  IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema='common' AND table_name='company')
  AND NOT EXISTS (
    SELECT 1
    FROM pg_constraint c
    JOIN pg_class t ON t.oid = c.conrelid
    JOIN pg_namespace n ON n.oid = t.relnamespace
    WHERE c.conname = 'fk_building_company__company'
      AND n.nspname = 'common'
      AND t.relname = 'building_company'
  )
  THEN
    ALTER TABLE common.building_company
      ADD CONSTRAINT fk_building_company__company
      FOREIGN KEY (company_id) REFERENCES common.company(id) ON DELETE CASCADE;
  END IF;
END $$;
