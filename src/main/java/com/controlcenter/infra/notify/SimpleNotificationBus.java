package com.controlcenter.infra.notify;

import com.controlcenter.core.notify.NotificationBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SimpleNotificationBus implements NotificationBus {
    private static final Logger log = LoggerFactory.getLogger(SimpleNotificationBus.class);
    @Override public void notify(UUID jobId) { log.info("[NotificationBus] Job {} atualizado", jobId); }
}
