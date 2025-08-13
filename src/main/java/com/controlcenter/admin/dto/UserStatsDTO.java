package com.controlcenter.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStatsDTO {
    private long totalUsers;
    private long activeUsers;
    private long inactiveUsers;
    private long lockedAccounts;
    private long pendingDeletion;
}