package com.controlcenter.admin.controller;

import com.controlcenter.admin.dto.UserStatsDTO;
import com.controlcenter.admin.service.AdminUserStatsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserStatsController {

    private final AdminUserStatsService userStatsService;

    @Operation(summary = "Obtém estatísticas gerais de usuários")
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/stats")
    public ResponseEntity<UserStatsDTO> getUserStats() {
        return ResponseEntity.ok(userStatsService.getStats());
    }
}
