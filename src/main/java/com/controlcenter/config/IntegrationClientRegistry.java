// com/controlcenter/config/IntegrationClientRegistry.java
package com.controlcenter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class IntegrationClientRegistry {

    private final IntegrationsProperties props;
    private final WebClient.Builder baseBuilder; // vem do WebClientConfig
    private final Map<String, WebClient> clients = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        props.getClients().forEach((name, p) -> {
            WebClient client = baseBuilder.clone()
                    .baseUrl(p.getBaseUrl())
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + p.getToken())
                    .build();
            clients.put(name, client);
        });
    }

    public WebClient get(String name) {
        WebClient c = clients.get(name);
        if (c == null) {
            throw new IllegalArgumentException("Integration not configured: " + name);
        }
        return c;
    }
}
