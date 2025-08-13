package com.controlcenter.admin.repository;

import com.controlcenter.entity.security.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminUserStatusRepository extends JpaRepository<UserStatus, UUID> {
    Optional<UserStatus> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}