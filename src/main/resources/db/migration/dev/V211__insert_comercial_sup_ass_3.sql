/* =============== SUPERVISOR 3 =============== */
SET search_path TO security;
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
  'a2000000-0000-0000-0000-000000000403','Supervisor','3','Supervisor 3',NULL,'sup3','sup3@empresa.com','sup3.pessoal@gmail.com',
  '300.200.403-00','1984-03-03',TRUE,E'$2a$12$EXM5g9yGl16L1G0jLcn0EunGo57X4VB4xb4.xI9Z/QWHMg0cmeNTS',NOW(),
  FALSE,NULL,FALSE,NULL,
  'local','light','America/Sao_Paulo',TRUE,0,'192.168.0.103',
  NOW(),'1.0',NULL,NOW(),NOW(),TRUE,
  'pt-BR','INVITED',NULL,'Sete Lagoas - MG',
  FALSE,NULL,'ACCEPTED',NULL,
  NULL,FALSE,'29d2d8e3-6165-4e80-a480-6ab4f6d7acd1','11111111-aaaa-4b11-9111-aaaaaaaaaaa1'
);

/* Assistentes do Supervisor 3 */
INSERT INTO users (
  id, first_name, last_name, full_name, social_name, username, email, personal_email,
  cpf, birth_date, email_verified, password, password_last_updated,
  account_locked, account_locked_at, account_deletion_requested, account_deletion_request_date,
  origin, interface_theme, timezone, notifications_enabled, login_attempts, last_password_change_ip,
  terms_accepted_at, privacy_policy_version, avatar, created_at, updated_at, first_login,
  preferred_language, invitation_status, account_suspended_reason, last_known_location,
  password_compromised, forced_logout_at, cookie_consent_status, manager_id,
  two_factor_secret, two_factor_enabled, status_id, position_id
)  VALUES
(
  'a2000000-0000-0000-0000-000000000431','Assistente','3-1','Assistente 3 - 1',NULL,'as31','as31@empresa.com','as31.pessoal@gmail.com',
  '300.200.431-00','1995-03-03',TRUE,E'$2a$12$EXM5g9yGl16L1G0jLcn0EunGo57X4VB4xb4.xI9Z/QWHMg0cmeNTS',NOW(),
  FALSE,NULL,FALSE,NULL,
  'local','light','America/Sao_Paulo',TRUE,0,'192.168.0.131',
  NOW(),'1.0',NULL,NOW(),NOW(),TRUE,
  'pt-BR','INVITED',NULL,'Sete Lagoas - MG',
  FALSE,NULL,'ACCEPTED','a2000000-0000-0000-0000-000000000403',
  NULL,FALSE,'29d2d8e3-6165-4e80-a480-6ab4f6d7acd1','22222222-bbbb-4b22-9222-bbbbbbbbbbb2'
),
(
  'a2000000-0000-0000-0000-000000000432','Assistente','3-2','Assistente 3 - 2',NULL,'as32','as32@empresa.com','as32.pessoal@gmail.com',
  '300.200.432-00','1996-03-03',TRUE,E'$2a$12$EXM5g9yGl16L1G0jLcn0EunGo57X4VB4xb4.xI9Z/QWHMg0cmeNTS',NOW(),
  FALSE,NULL,FALSE,NULL,
  'local','light','America/Sao_Paulo',TRUE,0,'192.168.0.132',
  NOW(),'1.0',NULL,NOW(),NOW(),TRUE,
  'pt-BR','INVITED',NULL,'Sete Lagoas - MG',
  FALSE,NULL,'ACCEPTED','a2000000-0000-0000-0000-000000000403',
  NULL,FALSE,'29d2d8e3-6165-4e80-a480-6ab4f6d7acd1','22222222-bbbb-4b22-9222-bbbbbbbbbbb2'
);