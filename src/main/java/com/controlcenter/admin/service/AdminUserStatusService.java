package com.controlcenter.admin.service;

import com.controlcenter.admin.dto.UserStatusDTO;
import com.controlcenter.admin.mapper.UserStatusMapper;
import com.controlcenter.admin.repository.AdminUserStatusRepository;
import com.controlcenter.entity.security.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminUserStatusService {

    private final AdminUserStatusRepository adminUserStatusRepository;

    @Transactional(readOnly = true)
    public List<UserStatusDTO> listAll() {
        return adminUserStatusRepository.findAll().stream().map(UserStatusMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public UserStatusDTO getById(UUID id) {
        return adminUserStatusRepository.findById(id).map(UserStatusMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("UserStatus não encontrado"));
    }

    @Transactional
    public UserStatusDTO create(UserStatusDTO dto) {
        if (adminUserStatusRepository.existsByNameIgnoreCase(dto.name())) {
            throw new IllegalArgumentException("Já existe UserStatus com nome: " + dto.name());
        }
        return UserStatusMapper.toDTO(adminUserStatusRepository.save(UserStatusMapper.toEntity(dto)));
    }

    @Transactional
    public UserStatusDTO update(UUID id, UserStatusDTO dto) {
        UserStatus e = adminUserStatusRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("UserStatus não encontrado"));
        if (!e.getName().equalsIgnoreCase(dto.name()) && adminUserStatusRepository.existsByNameIgnoreCase(dto.name())) {
            throw new IllegalArgumentException("Já existe UserStatus com nome: " + dto.name());
        }
        e.setName(dto.name());
        e.setDescription(dto.description());
        e.setActive(dto.active());
        return UserStatusMapper.toDTO(adminUserStatusRepository.save(e));
    }

    @Transactional
    public void delete(UUID id) {
        if (!adminUserStatusRepository.existsById(id)) throw new IllegalArgumentException("UserStatus não encontrado");
        adminUserStatusRepository.deleteById(id);
    }
}
