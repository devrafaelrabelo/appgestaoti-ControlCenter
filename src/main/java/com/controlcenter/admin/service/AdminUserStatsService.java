package com.controlcenter.admin.service;

import com.controlcenter.admin.dto.UserStatsDTO;
import com.controlcenter.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserStatsService {

    private final UserRepository userRepository;

    public UserStatsDTO getStats() {
        long total = userRepository.count();
        long active = userRepository.countByStatus_NameIgnoreCase("active");
        long inactive = total - active;
        long locked = userRepository.countByAccountLockedTrue();
        long pendingDeletion = userRepository.countByAccountDeletionRequestedTrue();

        return new UserStatsDTO(total, active, inactive, locked, pendingDeletion);
    }
}
