SET search_path TO security;

/* =============== SUPERVISOR 1 =============== */
INSERT INTO users (
  id, first_name, last_name, full_name, social_name, username, email, personal_email,
  cpf, birth_date, email_verified, password, password_last_updated,
  account_locked, account_locked_at, account_deletion_requested, account_deletion_request_date,
  origin, interface_theme, timezone, notifications_enabled, login_attempts, last_password_change_ip,
  terms_accepted_at, privacy_policy_version, avatar, created_at, updated_at, first_login,
  preferred_language, invitation_status, account_suspended_reason, last_known_location,
  password_compromised, forced_logout_at, cookie_consent_status, manager_id,
  two_factor_secret, two_factor_enabled, status_id, position_id
) VALUES (
  'a2000000-0000-0000-0000-000000000401','Supervisor','1','Supervisor 1',NULL,'sup1','sup1@empresa.com','sup1.pessoal@gmail.com',
  '300.200.401-00','1980-01-01',TRUE,E'$2a$12$EXM5g9yGl16L1G0jLcn0EunGo57X4VB4xb4.xI9Z/QWHMg0cmeNTS',NOW(),
  FALSE,NULL,FALSE,NULL,
  'local','light','America/Sao_Paulo',TRUE,0,'192.168.0.101',
  NOW(),'1.0',NULL,NOW(),NOW(),TRUE,
  'pt-BR','INVITED',NULL,'Sete Lagoas - MG',
  FALSE,NULL,'ACCEPTED',NULL,
  NULL,FALSE,'29d2d8e3-6165-4e80-a480-6ab4f6d7acd1','11111111-aaaa-4b11-9111-aaaaaaaaaaa1'
);

/* Assistentes do Supervisor 1 */
INSERT INTO users (
  id, first_name, last_name, full_name, social_name, username, email, personal_email,
  cpf, birth_date, email_verified, password, password_last_updated,
  account_locked, account_locked_at, account_deletion_requested, account_deletion_request_date,
  origin, interface_theme, timezone, notifications_enabled, login_attempts, last_password_change_ip,
  terms_accepted_at, privacy_policy_version, avatar, created_at, updated_at, first_login,
  preferred_language, invitation_status, account_suspended_reason, last_known_location,
  password_compromised, forced_logout_at, cookie_consent_status, manager_id,
  two_factor_secret, two_factor_enabled, status_id, position_id
) VALUES
(
  'a2000000-0000-0000-0000-000000000411','Assistente','1-1','Assistente 1 - 1',NULL,'as11','as11@empresa.com','as11.pessoal@gmail.com',
  '300.200.411-00','1995-01-01',TRUE,E'$2a$12$EXM5g9yGl16L1G0jLcn0EunGo57X4VB4xb4.xI9Z/QWHMg0cmeNTS',NOW(),
  FALSE,NULL,FALSE,NULL,
  'local','light','America/Sao_Paulo',TRUE,0,'192.168.0.111',
  NOW(),'1.0',NULL,NOW(),NOW(),TRUE,
  'pt-BR','INVITED',NULL,'Sete Lagoas - MG',
  FALSE,NULL,'ACCEPTED','a2000000-0000-0000-0000-000000000401',
  NULL,FALSE,'29d2d8e3-6165-4e80-a480-6ab4f6d7acd1','22222222-bbbb-4b22-9222-bbbbbbbbbbb2'
),
(
  'a2000000-0000-0000-0000-000000000412','Assistente','1-2','Assistente 1 - 2',NULL,'as12','as12@empresa.com','as12.pessoal@gmail.com',
  '300.200.412-00','1996-01-01',TRUE,E'$2a$12$EXM5g9yGl16L1G0jLcn0EunGo57X4VB4xb4.xI9Z/QWHMg0cmeNTS',NOW(),
  FALSE,NULL,FALSE,NULL,
  'local','light','America/Sao_Paulo',TRUE,0,'192.168.0.112',
  NOW(),'1.0',NULL,NOW(),NOW(),TRUE,
  'pt-BR','INVITED',NULL,'Sete Lagoas - MG',
  FALSE,NULL,'ACCEPTED','a2000000-0000-0000-0000-000000000401',
  NULL,FALSE,'29d2d8e3-6165-4e80-a480-6ab4f6d7acd1','22222222-bbbb-4b22-9222-bbbbbbbbbbb2'
);