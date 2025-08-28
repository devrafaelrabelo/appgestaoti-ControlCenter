package com.controlcenter.admin.controller;

import com.controlcenter.common.dto.*;
import com.controlcenter.common.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/buildings")
@RequiredArgsConstructor
@Tag(name = "Admin • Buildings", description = "Gestão de prédios/locais e vínculos com empresas (IDs no corpo)")
@SecurityRequirement(name = "bearerAuth")
public class AdminBuildingController {

    private final BuildingService buildingService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('building:create')")
    @Operation(
            summary = "Criar prédio/local",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BuildingUpsertDTO.class),
                            examples = @ExampleObject(value = """
                    {
                      "name": "Matriz SP",
                      "code": "MAT-SP",
                      "description": "Sede administrativa",
                      "address": {
                        "street": "Av. Paulista",
                        "number": "1000",
                        "complement": "12º andar",
                        "neighborhood": "Bela Vista",
                        "city": "São Paulo",
                        "state": "SP",
                        "country": "Brasil",
                        "postalCode": "01310-100"
                      },
                      "active": true
                    }
                    """)
                    )
            )
    )
    public ResponseEntity<BuildingDTO> create(@RequestBody @Valid BuildingUpsertDTO dto) {
        return ResponseEntity.ok(buildingService.create(dto));
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('building:update')")
    @Operation(
            summary = "Atualizar prédio/local (ID no corpo)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BuildingUpsertDTO.class),
                            examples = @ExampleObject(value = """
                    {
                      "id": "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee",
                      "name": "Matriz SP - Reformada",
                      "code": "MAT-SP",
                      "description": "Sede administrativa (reformada)",
                      "address": {
                        "street": "Av. Paulista",
                        "number": "1000",
                        "city": "São Paulo",
                        "state": "SP",
                        "country": "Brasil",
                        "postalCode": "01310-100"
                      },
                      "active": true
                    }
                    """)
                    )
            )
    )
    public ResponseEntity<BuildingDTO> update(@RequestBody @Valid BuildingUpsertDTO dto) {
        return ResponseEntity.ok(buildingService.update(dto)); // <<< só o DTO
    }

    @PostMapping("/get")
    @PreAuthorize("hasAuthority('building:read')")
    @Operation(
            summary = "Detalhar prédio/local (ID no corpo)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = IdBody.class),
                            examples = @ExampleObject(value = """
                    { "id": "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee" }
                    """)
                    )
            )
    )
    public ResponseEntity<BuildingDTO> get(@RequestBody @Valid IdBody body) {
        return ResponseEntity.ok(buildingService.get(body.id()));
    }

    @PostMapping("/companies/link")
    @PreAuthorize("hasAuthority('building:link')")
    @Operation(
            summary = "Vincular empresa a um prédio (IDs no corpo)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BuildingCompanyLinkBody.class),
                            examples = @ExampleObject(value = """
                    {
                      "buildingId": "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee",
                      "companyId":  "11111111-2222-3333-4444-555555555555"
                    }
                    """)
                    )
            )
    )
    public ResponseEntity<Void> linkCompany(@RequestBody @Valid BuildingCompanyLinkBody body) {
        buildingService.linkCompany(body.buildingId(), body.companyId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/companies/unlink")
    @PreAuthorize("hasAuthority('building:unlink')")
    @Operation(
            summary = "Desvincular empresa de um prédio (IDs no corpo)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BuildingCompanyLinkBody.class),
                            examples = @ExampleObject(value = """
                    {
                      "buildingId": "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee",
                      "companyId":  "11111111-2222-3333-4444-555555555555"
                    }
                    """)
                    )
            )
    )
    public ResponseEntity<Void> unlinkCompany(@RequestBody @Valid BuildingCompanyLinkBody body) {
        buildingService.unlinkCompany(body.buildingId(), body.companyId());
        return ResponseEntity.noContent().build();
    }
}
