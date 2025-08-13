/* =========================
   Usuário do setor de TI
   ========================= */
SET search_path TO security;

INSERT INTO users (
  id,
  first_name,
  last_name,
  full_name,
  social_name,
  username,
  email,
  personal_email,
  cpf,
  birth_date,
  email_verified,
  password,
  password_last_updated,
  account_locked,
  account_locked_at,
  account_deletion_requested,
  account_deletion_request_date,
  origin,
  interface_theme,
  timezone,
  notifications_enabled,
  login_attempts,
  last_password_change_ip,
  terms_accepted_at,
  privacy_policy_version,
  avatar,
  created_at,
  updated_at,
  first_login,
  preferred_language,
  invitation_status,
  account_suspended_reason,
  last_known_location,
  password_compromised,
  forced_logout_at,
  cookie_consent_status,
  manager_id,
  two_factor_secret,
  two_factor_enabled,
  status_id
) VALUES (
  'a3000000-0000-0000-0000-000000000001', -- id
  'Ti',                                   -- first_name
  'User',                                 -- last_name
  'Ti User',                              -- full_name
  NULL,                                   -- social_name
  'tiuser1',                              -- username
  'ti.user@empresa.com',                  -- email
  'ti.user.pessoal@gmail.com',            -- personal_email
  '100.100.100-01',                       -- cpf
  '1991-01-01',                           -- birth_date
  true,                                   -- email_verified
  E'$2a$12$EXM5g9yGl16L1G0jLcn0EunGo57X4VB4xb4.xI9Z/QWHMg0cmeNTS', -- senha: Admin@123
  NOW(),                                  -- password_last_updated
  false,                                  -- account_locked
  NULL,                                   -- account_locked_at
  false,                                  -- account_deletion_requested
  NULL,                                   -- account_deletion_request_date
  'local',                                -- origin
  'light',                                -- interface_theme
  'America/Sao_Paulo',                    -- timezone
  true,                                   -- notifications_enabled
  0,                                      -- login_attempts
  '192.168.0.22',                         -- last_password_change_ip
  NOW(),                                  -- terms_accepted_at
  '1.0',                                  -- privacy_policy_version
  NULL,                                   -- avatar
  NOW(),                                  -- created_at
  NOW(),                                  -- updated_at
  true,                                   -- first_login
  'pt-BR',                                -- preferred_language
  'INVITED',                              -- invitation_status
  NULL,                                   -- account_suspended_reason
  'Sete Lagoas - MG',                     -- last_known_location
  false,                                  -- password_compromised
  NULL,                                   -- forced_logout_at
  'ACCEPTED',                             -- cookie_consent_status
  NULL,                                   -- manager_id
  NULL,                                   -- two_factor_secret
  false,                                  -- two_factor_enabled
  '29d2d8e3-6165-4e80-a480-6ab4f6d7acd1'  -- status_id (ACTIVE)
);

SET search_path TO "user";
INSERT INTO user_department (user_id, department_id) VALUES
  ('a3000000-0000-0000-0000-000000000001', '16c51bc2-2883-4077-9fdf-d625f4fcaca5'); -- TI