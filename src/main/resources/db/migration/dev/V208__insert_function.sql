/* ===========================================
   2) DML: inserir Functions base
   =========================================== */
SET search_path TO common;

-- V208__insert_function.sql (alternativa sem UNIQUE)
INSERT INTO common.function (id, name, description)
SELECT '33333333-cccc-4c33-9333-ccccccccccc3', 'Supervisor', 'Responsável por coordenação de equipe e metas'
WHERE NOT EXISTS (SELECT 1 FROM common.function WHERE name = 'Supervisor');

INSERT INTO common.function (id, name, description)
SELECT '44444444-dddd-4d44-9444-dddddddddddd', 'Vendedor', 'Responsável por prospecção, negociação e fechamento'
WHERE NOT EXISTS (SELECT 1 FROM common.function WHERE name = 'Vendedor');