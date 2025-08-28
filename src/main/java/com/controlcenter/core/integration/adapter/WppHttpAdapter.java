package com.controlcenter.core.integration.adapter;

import com.controlcenter.config.IntegrationClientRegistry;
import com.controlcenter.core.integration.port.WppPort;
import com.controlcenter.core.integration.port.WppSendMessageCommand;
import com.controlcenter.core.integration.port.WppSendMessageResult;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WppHttpAdapter implements WppPort {

    private final IntegrationClientRegistry clients;
    private WebClient c() { return clients.get("wpp"); }

    @TimeLimiter(name = "wpp")
    @CircuitBreaker(name = "wpp")
    @Retry(name = "wpp")
    @Override
    public WppSendMessageResult sendMessage(WppSendMessageCommand cmd) {
        return c().post()
                .uri("/message/sendText") // ajuste se sua rota for diferente
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cmd)
                .retrieve()
                .bodyToMono(WppSendMessageResult.class)
                .block();
    }
}
