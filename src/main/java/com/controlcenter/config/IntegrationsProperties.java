package com.controlcenter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "integrations")
@Getter @Setter
public class IntegrationsProperties {
    private Map<String, ServiceProps> clients;
    @Getter @Setter
    public static class ServiceProps {
        private String baseUrl;
        private String token;
        private Integer timeoutMs;
    }
}
