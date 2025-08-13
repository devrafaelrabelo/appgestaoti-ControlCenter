package com.controlcenter.admin.mapper;

import com.controlcenter.admin.dto.UserStatusDTO;
import com.controlcenter.entity.security.UserStatus;

public class UserStatusMapper {

    public static UserStatusDTO toDTO(UserStatus e) {
        return new UserStatusDTO(e.getId(), e.getName(), e.getDescription(), e.isActive());
    }

    public static UserStatus toEntity(UserStatusDTO dto) {
        return UserStatus.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .active(dto.active())
                .build();
    }
}