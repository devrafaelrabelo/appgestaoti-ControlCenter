package com.controlcenter.core.notify;

import java.util.UUID;

public interface NotificationBus {
    void notify(UUID jobId);
}
