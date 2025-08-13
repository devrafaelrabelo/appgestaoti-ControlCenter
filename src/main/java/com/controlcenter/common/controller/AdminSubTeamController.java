package com.controlcenter.common.controller;

import com.controlcenter.common.dto.SubTeamDTO;
import com.controlcenter.common.mapper.SubTeamMapper;
import com.controlcenter.common.service.SubTeamService;
import com.controlcenter.entity.common.SubTeam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/workforce/subteams")
@RequiredArgsConstructor
@Tag(name = "Admin - SubEquipes", description = "Operações administrativas sobre subequipes")
public class AdminSubTeamController {

    private final SubTeamService subTeamService;

    @PostMapping
    @Operation(
            summary = "Criar nova subequipe",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da subequipe",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = SubTeamDTO.class),
                            examples = @ExampleObject(value = """
                            {
                              "name": "Equipe de Monitoramento Noturno",
                              "description": "Turno noturno da equipe de monitoramento",
                              "teamId": "team-uuid-aqui",
                              "managerId": "user-uuid-aqui"
                            }
                            """)
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Subequipe criada com sucesso")
    public ResponseEntity<SubTeamDTO> create(@Valid @RequestBody SubTeamDTO dto) {
        SubTeam subTeam = SubTeamMapper.toEntity(dto);
        SubTeam saved = subTeamService.create(subTeam, dto.teamId(), dto.managerId());
        return ResponseEntity.ok(SubTeamMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar subequipe existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados da subequipe",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = SubTeamDTO.class),
                            examples = @ExampleObject(value = """
                            {
                              "name": "Equipe de Monitoramento Dia",
                              "description": "Turno diurno atualizado",
                              "teamId": "team-uuid-aqui",
                              "managerId": "user-uuid-aqui"
                            }
                            """)
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subequipe atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Subequipe não encontrada")
    })
    public ResponseEntity<SubTeamDTO> update(
            @Parameter(description = "ID da subequipe", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody SubTeamDTO dto) {
        SubTeam subTeam = SubTeamMapper.toEntity(dto);
        SubTeam updated = subTeamService.update(id, subTeam, dto.teamId(), dto.managerId());
        return ResponseEntity.ok(SubTeamMapper.toDTO(updated));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar subequipe por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subequipe encontrada"),
            @ApiResponse(responseCode = "404", description = "Subequipe não encontrada")
    })
    public ResponseEntity<SubTeam> findById(
            @Parameter(description = "ID da subequipe", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(subTeamService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas as subequipes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<SubTeam>> findAll() {
        return ResponseEntity.ok(subTeamService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar subequipe")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Subequipe excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Subequipe não encontrada")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID da subequipe", required = true)
            @PathVariable UUID id) {
        subTeamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
