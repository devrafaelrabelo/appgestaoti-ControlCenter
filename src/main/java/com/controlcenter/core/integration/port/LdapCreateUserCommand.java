package com.controlcenter.core.integration.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value @Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // não envia password quando for null
public class LdapCreateUserCommand {
    @JsonProperty("first_name") String firstName;
    @JsonProperty("last_name")  String lastName;
    String username;
    @JsonProperty("org_unit_path") String orgUnitPath;
    String floor;
    String gestor;
    String password; // pode ser null
}