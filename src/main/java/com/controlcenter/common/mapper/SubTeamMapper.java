package com.controlcenter.common.mapper;

import com.controlcenter.entity.common.SubTeam;
import com.controlcenter.common.dto.SubTeamDTO;

public class SubTeamMapper {

    public static SubTeamDTO toDTO(SubTeam subTeam) {
        return new SubTeamDTO(
                subTeam.getId(),
                subTeam.getName(),
                subTeam.getDescription(),
                subTeam.getTeam() != null ? subTeam.getTeam().getId() : null,
                subTeam.getManager() != null ? subTeam.getManager().getId() : null
        );
    }

    public static SubTeam toEntity(SubTeamDTO dto) {
        SubTeam sub = new SubTeam();
        sub.setId(dto.id());
        sub.setName(dto.name());
        sub.setDescription(dto.description());
        // team e manager devem ser setados no service a partir dos IDs
        return sub;
    }
}
