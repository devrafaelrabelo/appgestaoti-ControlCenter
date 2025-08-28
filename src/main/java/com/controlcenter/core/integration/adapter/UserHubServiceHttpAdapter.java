package com.controlcenter.core.integration.adapter;

import com.controlcenter.config.IntegrationClientRegistry;
import com.controlcenter.core.integration.port.UserHubServicePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHubServiceHttpAdapter implements UserHubServicePort {

    private final IntegrationClientRegistry clients;

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public boolean health() {
        return Boolean.TRUE.equals(
                clients.get("userhub").get()
                        .uri("/userhub/health")
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .onErrorReturn(false)
                        .block()
        );
    }
}
