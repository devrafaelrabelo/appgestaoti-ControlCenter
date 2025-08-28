package com.controlcenter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
@Configuration
@EnableConfigurationProperties(IntegrationsProperties.class)
public class WebClientConfig {

    /**
     * Builder base, sem baseUrl e sem Authorization por padrão.
     * O IntegrationClientRegistry é quem aplicará baseUrl/token conforme cada integração.
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        HttpClient http = HttpClient.create()
                // timeout “hard” de resposta (cada cliente ainda pode ter seu próprio via registry)
                .responseTimeout(Duration.ofSeconds(30));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(http))
                // aumenta limite de payload em memória (ex.: 4MB)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(c -> c.defaultCodecs().maxInMemorySize(4 * 1024 * 1024))
                        .build())
                // filtros de logging simples (remova se não quiser logs)
                .filter(logRequest())
                .filter(logResponse());
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(req -> {
            // log simples (evita imprimir bodies/sigilos)
            System.out.println("[WebClient] -> " + req.method() + " " + req.url());
            return reactor.core.publisher.Mono.just(req);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(res -> {
            System.out.println("[WebClient] <- " + res.statusCode());
            return reactor.core.publisher.Mono.just(res);
        });
    }
}
