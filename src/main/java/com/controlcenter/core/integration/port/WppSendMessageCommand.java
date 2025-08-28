package com.controlcenter.core.integration.port;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class WppSendMessageCommand {
    String session;  // sessão do WPPConnect
    String phone;    // E.164 (ex: 5531999999999)
    String message;  // texto
}
