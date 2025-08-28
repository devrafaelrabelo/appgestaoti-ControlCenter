package com.controlcenter.core.integration.port;

import lombok.Value;

@Value
public class WppSendMessageResult {
    boolean success;
    String detail;
}
