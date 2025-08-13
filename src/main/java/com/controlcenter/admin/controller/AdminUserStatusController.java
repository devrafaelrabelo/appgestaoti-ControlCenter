package com.controlcenter.admin.controller;

import com.controlcenter.admin.dto.UserStatusDTO;
import com.controlcenter.admin.service.AdminUserStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/user-status")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AdminUserStatusController {

    private final AdminUserStatusService adminUserStatusService;

    @Operation(summary = "Listar todos os status de usuário")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    @PreAuthorize("hasAuthority('userstatus:read')")
    public ResponseEntity<List<UserStatusDTO>> listAll() {
        return ResponseEntity.ok(adminUserStatusService.listAll());
    }

    @Operation(summary = "Buscar status por ID")
    @ApiResponse(responseCode = "200", description = "Encontrado")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('userstatus:read')")
    public ResponseEntity<UserStatusDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(adminUserStatusService.getById(id));
    }

    @Operation(summary = "Criar novo status")
    @PostMapping
    @PreAuthorize("hasAuthority('userstatus:create')")
    public ResponseEntity<UserStatusDTO> create(@RequestBody UserStatusDTO dto) {
        return ResponseEntity.ok(adminUserStatusService.create(dto));
    }

    @Operation(summary = "Atualizar status por ID")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('userstatus:update')")
    public ResponseEntity<UserStatusDTO> update(@PathVariable UUID id, @RequestBody UserStatusDTO dto) {
        return ResponseEntity.ok(adminUserStatusService.update(id, dto));
    }

    @Operation(summary = "Deletar status por ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('userstatus:delete')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        adminUserStatusService.delete(id);
        return ResponseEntity.ok().build();
    }
}
