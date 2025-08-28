package com.controlcenter.core.integration.port;

public interface WppPort {
    WppSendMessageResult sendMessage(WppSendMessageCommand cmd);
}
