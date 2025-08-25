SET search_path TO common;

-- ===========================
-- TABELA: BUILDING (Prédios/Locais)
-- ===========================
CREATE TABLE common.building (
  id UUID PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  code VARCHAR(50) UNIQUE,
  description TEXT,
  address_id UUID REFERENCES common.address(id),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE common.building IS 'Cadastro de prédios/locais físicos da organização.';
COMMENT ON COLUMN common.building.code IS 'Código interno único do prédio/local.';

CREATE INDEX idx_building_name   ON common.building (name);
CREATE INDEX idx_building_active ON common.building (active);

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
-- TABELA DE RELAÇÃO N:N
-- ===========================
CREATE TABLE common.building_company (
  id UUID PRIMARY KEY,
  building_id UUID NOT NULL REFERENCES common.building(id) ON DELETE CASCADE,
  company_id  UUID NOT NULL REFERENCES common.company(id)  ON DELETE CASCADE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  UNIQUE (building_id, company_id)
);

CREATE INDEX idx_building_company_building ON common.building_company (building_id);
CREATE INDEX idx_building_company_company  ON common.building_company (company_id);

COMMENT ON TABLE common.building_company IS 'Vínculo N:N entre prédios/locais e empresas (CNPJs).';
