package com.controlcenter.common.repository;

import com.controlcenter.entity.common.SubTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubTeamRepository extends JpaRepository<SubTeam, UUID> {
    // Aqui você pode adicionar métodos customizados no futuro, se necessário
}
