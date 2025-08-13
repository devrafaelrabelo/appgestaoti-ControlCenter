package com.controlcenter.common.controller;

import com.controlcenter.common.dto.SubTeamTeamRequest;
import com.controlcenter.common.service.SubTeamTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/subteams/team")
@RequiredArgsConstructor
public class AdminSubTeamTeamController {

    private final SubTeamTeamService subTeamTeamService;

    @PostMapping("/assign")
    public ResponseEntity<Void> assign(@RequestBody SubTeamTeamRequest request) {
        subTeamTeamService.assignSubTeamToTeam(request.subTeamId(), request.teamId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove/{subTeamId}")
    public ResponseEntity<Void> remove(@PathVariable UUID subTeamId) {
        subTeamTeamService.removeSubTeamFromTeam(subTeamId);
        return ResponseEntity.ok().build();
    }
}
