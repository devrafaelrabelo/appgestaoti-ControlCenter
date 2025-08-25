package com.controlcenter.admin.controller;

import com.controlcenter.common.dto.BuildingCompanyLinkDTO;
import com.controlcenter.common.dto.BuildingDTO;
import com.controlcenter.common.dto.BuildingUpsertDTO;
import com.controlcenter.common.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/buildings")
@AllArgsConstructor
public class AdminBuildingController {

    private final BuildingService buildingService;

    @PostMapping
    @Operation(summary = "Criar prédio/local")
    public ResponseEntity<BuildingDTO> create(@RequestBody BuildingUpsertDTO dto) {
        return ResponseEntity.ok(buildingService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar prédio/local")
    public ResponseEntity<BuildingDTO> update(@PathVariable UUID id, @RequestBody BuildingUpsertDTO dto) {
        return ResponseEntity.ok(buildingService.update(id, dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar prédio/local")
    public ResponseEntity<BuildingDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(buildingService.get(id));
    }

    @PostMapping("/{id}/companies")
    @Operation(summary = "Vincular empresa (CNPJ) a um prédio")
    public ResponseEntity<?> linkCompany(@PathVariable UUID id, @RequestBody BuildingCompanyLinkDTO body) {
        buildingService.linkCompany(id, body.companyId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/companies/{companyId}")
    @Operation(summary = "Desvincular empresa (CNPJ) de um prédio")
    public ResponseEntity<?> unlinkCompany(@PathVariable UUID id, @PathVariable UUID companyId) {
        buildingService.unlinkCompany(id, companyId);
        return ResponseEntity.noContent().build();
    }
}