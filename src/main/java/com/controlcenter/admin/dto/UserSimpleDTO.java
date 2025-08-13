package com.controlcenter.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

public record UserSimpleDTO(UUID id, String name) {}