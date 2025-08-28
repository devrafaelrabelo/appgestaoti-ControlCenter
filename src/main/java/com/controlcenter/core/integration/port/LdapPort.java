package com.controlcenter.core.integration.port;

import jakarta.validation.constraints.NotBlank;

public interface LdapPort {
    boolean userExists(String username);

    void createUser(LdapCreateUserCommand cmd); // password pode ser null

    String firstAvailableUsername(String fullName, int limit);

    void reactivateUser(String username);

    void disableUser(@NotBlank String username);
}