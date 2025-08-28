package com.controlcenter.core.integration.port;

public interface GooglePort {
    boolean testEmailConnection();      // GET /userhub/email/test-connection
    boolean emailExists(String email);  // POST /userhub/email/exists
}