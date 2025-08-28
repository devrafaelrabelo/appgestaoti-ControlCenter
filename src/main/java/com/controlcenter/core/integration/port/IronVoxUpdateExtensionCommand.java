package com.controlcenter.core.integration.port;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class IronVoxUpdateExtensionCommand {
    String extension;   // ex: "611"
    String userName;    // ex: "Rafael Rabelo"
    String queue;       // ex: "Comercial"
}