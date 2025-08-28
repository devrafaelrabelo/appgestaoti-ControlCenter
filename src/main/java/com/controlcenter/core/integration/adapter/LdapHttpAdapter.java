package com.controlcenter.core.integration.adapter;

import com.controlcenter.config.IntegrationClientRegistry;
import com.controlcenter.core.integration.port.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LdapHttpAdapter implements LdapPort {

    private final IntegrationClientRegistry clients;
    private WebClient c() { return clients.get("userhub"); }

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public boolean userExists(String username) {
        return Boolean.TRUE.equals(
                c().post().uri("/userhub/user/exists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("username", username))
                        .retrieve().bodyToMono(Boolean.class)
                        .defaultIfEmpty(false).block()
        );
    }

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public void createUser(LdapCreateUserCommand cmd) {
        c().post().uri("/userhub/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cmd)
                .retrieve().toBodilessEntity().block();
    }

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public void disableUser(String username) {
        c().patch().uri("/userhub/user/disable")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("username", username))
                .retrieve().toBodilessEntity().block();
    }

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public String firstAvailableUsername(String fullName, int limit) {
        return c().post().uri("/userhub/user/first-available")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("full_name", fullName, "limit", limit))
                .retrieve().bodyToMono(String.class).block();
    }

    @TimeLimiter(name="userhub") @CircuitBreaker(name="userhub") @Retry(name="userhub")
    @Override
    public void reactivateUser(String username) {
        c().patch().uri("/userhub/user/reactivate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("username", username))
                .retrieve().toBodilessEntity().block();
    }
}
