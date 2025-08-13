package com.controlcenter.common.controller;

import com.controlcenter.common.dto.UserSubTeamRequest;
import com.controlcenter.common.service.UserSubTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subteams/users")
@RequiredArgsConstructor
public class AdminUserSubTeamController {

    private final UserSubTeamService userSubTeamService;

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody UserSubTeamRequest request) {
        userSubTeamService.addUserToSubTeam(request.userId(), request.subTeamId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeUser(@RequestBody UserSubTeamRequest request) {
        userSubTeamService.removeUserFromSubTeam(request.userId(), request.subTeamId());
        return ResponseEntity.ok().build();
    }
}
