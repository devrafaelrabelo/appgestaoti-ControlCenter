package com.controlcenter.admin.controller;

import com.controlcenter.core.integration.port.LdapCreateUserCommand;
import com.controlcenter.core.integration.port.LdapPort;
import com.controlcenter.core.integration.port.LdapUserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/ldap/users")
@RequiredArgsConstructor
public class AdminLdapUserController {

    private final LdapPort ldap;

    // POST /exists  -> { "username": "rafael.rabelo" }
    @PostMapping("/exists")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ExistsResponse> exists(@Valid @RequestBody ExistsRequest req) {
        boolean exists = ldap.userExists(req.username());
        return ResponseEntity.ok(new ExistsResponse(exists));
    }

    // POST /create  -> payload completo (password pode ser null)
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<Void> create(@Valid @RequestBody LdapCreateUserCommand cmd) {
        ldap.createUser(cmd);
        return ResponseEntity.ok().build();
    }

    // PATCH /disable -> { "username": "rafael.rabelo" }
    @PatchMapping("/disable")
    @PreAuthorize("hasAuthority('user:disable')")
    public ResponseEntity<Void> disable(@Valid @RequestBody UsernameRequest req) {
        ldap.disableUser(req.username());
        return ResponseEntity.ok().build();
    }

    // POST /first-available -> { "full_name": "...", "limit": 1000 }
    @PostMapping("/first-available")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<FirstAvailableResponse> firstAvailable(@Valid @RequestBody FirstAvailableRequest req) {
        String suggestion = ldap.firstAvailableUsername(req.fullName(), req.limit());
        return ResponseEntity.ok(new FirstAvailableResponse(suggestion));
    }

    // PATCH /reactivate -> { "username": "rafael.rabelo" }
    @PatchMapping("/reactivate")
    @PreAuthorize("hasAuthority('user:reactivate')")
    public ResponseEntity<Void> reactivate(@Valid @RequestBody UsernameRequest req) {
        ldap.reactivateUser(req.username());
        return ResponseEntity.ok().build();
    }

    // ===== DTOs enxutos, só com o necessário =====
    public record ExistsRequest(@NotBlank String username) { }
    public record ExistsResponse(boolean exists) { }

    public record UsernameRequest(@NotBlank String username) { }

    public record FirstAvailableRequest(@NotBlank String fullName, @Min(1) int limit) { }
    public record FirstAvailableResponse(String suggestion) { }
}
