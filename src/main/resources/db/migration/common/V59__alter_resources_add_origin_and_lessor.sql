ALTER TABLE resource.resources
    ADD COLUMN origin VARCHAR(20) NOT NULL DEFAULT 'OWNED',
    ADD COLUMN lessor_company_name VARCHAR(255);