package com.controlcenter.core.integration.adapter;

import com.controlcenter.config.IntegrationClientRegistry;
import com.controlcenter.core.integration.port.IronVoxPort;
import com.controlcenter.core.integration.port.IronVoxResult;
import com.controlcenter.core.integration.port.IronVoxUpdateExtensionCommand;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class IronVoxHttpAdapter implements IronVoxPort {

    private final IntegrationClientRegistry clients;
    private WebClient c() { return clients.get("ironvox"); } // usa o registry

    @TimeLimiter(name = "ironvox")
    @CircuitBreaker(name = "ironvox")
    @Retry(name = "ironvox")
    @Override
    public IronVoxResult updateExtension(IronVoxUpdateExtensionCommand cmd) {
        return c().post()
                .uri("/ironvox/atualizar-ramal") // ajuste para sua rota real, se diferente
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cmd)
                .retrieve()
                .bodyToMono(IronVoxResult.class)
                .block();
    }
}
