package com.controlcenter.core.integration.port;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class GoogleCreateMailboxCommand {
    String primaryEmail; // ex: "user@empresa.com"
    String givenName;    // nome
    String familyName;   // sobrenome
    String tempPassword; // senha inicial
}
