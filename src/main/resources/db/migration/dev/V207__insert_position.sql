/* ===========================================
   2) DML: inserir Positions
   =========================================== */


-- Positions (cargos)
INSERT INTO common.position (id, name, description) VALUES
  ('11111111-aaaa-4b11-9111-aaaaaaaaaaa1', 'Supervisor Comercial', 'Cargo de supervisão no setor Comercial'),
  ('22222222-bbbb-4b22-9222-bbbbbbbbbbb2', 'Assistente Comercial', 'Cargo de apoio/assistência no setor Comercial')
ON CONFLICT (name) DO NOTHING;