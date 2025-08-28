package com.controlcenter.core.integration.adapter;

import com.controlcenter.config.IntegrationClientRegistry;
import com.controlcenter.core.integration.port.GooglePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GoogleHttpAdapter implements GooglePort {

    private final IntegrationClientRegistry clients;

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public boolean testEmailConnection() {
        return Boolean.TRUE.equals(
                clients.get("userhub").get()
                        .uri("/userhub/email/test-connection")
                        .retrieve().bodyToMono(Boolean.class)
                        .onErrorReturn(false).block()
        );
    }

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public boolean emailExists(String email) {
        return Boolean.TRUE.equals(
                clients.get("userhub").post()
                        .uri("/userhub/email/exists")
                        .bodyValue(java.util.Map.of("email", email))
                        .retrieve().bodyToMono(Boolean.class)
                        .defaultIfEmpty(false).block()
        );
    }
}

