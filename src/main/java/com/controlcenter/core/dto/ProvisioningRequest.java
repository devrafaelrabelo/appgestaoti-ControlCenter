package com.controlcenter.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

/** DTO recebido do frontend. Pode usar camelCase no JSON.
 *  @JsonProperty permite snake_case também, se vier. */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProvisioningRequest(
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("full_name") String fullName,
        String username,
        @JsonProperty("username_limit") Integer usernameLimit,
        @JsonProperty("org_unit_path") String orgUnitPath,
        String floor,
        String gestor,
        String password,     // pode ser null
        String email,
        @JsonProperty("requested_by") UUID requestedBy
) {}