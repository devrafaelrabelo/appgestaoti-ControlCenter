package com.controlcenter.common.controller;

import com.controlcenter.entity.common.Team;
import com.controlcenter.common.dto.TeamDTO;
import com.controlcenter.common.mapper.TeamMapper;
import com.controlcenter.common.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/workforce/teams")
@RequiredArgsConstructor
public class AdminTeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDTO> create(@RequestBody TeamDTO dto) {
        Team team = TeamMapper.toEntity(dto);
        Team saved = teamService.create(team, dto.supervisorId());
        return ResponseEntity.ok(TeamMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable UUID id, @RequestBody TeamDTO dto) {
        Team team = TeamMapper.toEntity(dto);
        Team updated = teamService.update(id, team, dto.supervisorId());
        return ResponseEntity.ok(TeamMapper.toDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(teamService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Team>> findAll() {
        return ResponseEntity.ok(teamService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
