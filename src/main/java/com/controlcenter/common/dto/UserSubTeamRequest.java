package com.controlcenter.common.dto;

import java.util.UUID;

public record UserSubTeamRequest(UUID userId, UUID subTeamId) {}
