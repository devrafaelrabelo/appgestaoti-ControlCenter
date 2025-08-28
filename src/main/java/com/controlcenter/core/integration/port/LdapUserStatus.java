package com.controlcenter.core.integration.port;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class LdapUserStatus {
    String username;
    boolean exists;
    boolean enabled;
    String dn;          // opcional
    String description; // opcional
}
