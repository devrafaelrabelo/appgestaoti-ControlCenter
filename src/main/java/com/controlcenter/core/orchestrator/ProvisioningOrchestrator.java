package com.controlcenter.core.orchestrator;

import com.controlcenter.core.dto.ProvisioningRequest;
import com.controlcenter.core.integration.port.GooglePort;
import com.controlcenter.core.integration.port.LdapCreateUserCommand;
import com.controlcenter.core.integration.port.LdapPort;
import com.controlcenter.core.integration.port.UserHubServicePort;
import com.controlcenter.core.job.JobStore;
import com.controlcenter.core.notify.NotificationBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProvisioningOrchestrator {

    private final LdapPort ldapPort;
    private final GooglePort googlePort;
    private final UserHubServicePort userHubService;
    private final JobStore jobStore;
    private final NotificationBus notifier;

    public JobResult provisionUser(ProvisioningRequest req, String idempotencyKey) {
        UUID jobId = jobStore.start("USER_PROVISIONING", idempotencyKey, req.requestedBy());

        try {
            safeStep(jobId, "USERHUB_HEALTH", () -> {
                boolean ok = userHubService.health();
                jobStore.step(jobId, "USERHUB_HEALTH", ok ? "OK" : "FAIL", Map.of("ok", ok));
            });

            jobStore.step(jobId, "USERNAME_CHOICE", "STARTED", null);
            String username = resolveUsername(req);
            jobStore.step(jobId, "USERNAME_CHOICE", "SUCCESS", Map.of("username", username));
            notifier.notify(jobId);

            jobStore.step(jobId, "LDAP_CREATE", "STARTED", null);
            ldapPort.createUser(toLdapCreateCommand(req, username));
            jobStore.step(jobId, "LDAP_CREATE", "SUCCESS", null);
            notifier.notify(jobId);

            if (req.email() != null && !req.email().isBlank()) {
                jobStore.step(jobId, "EMAIL_CHECK", "STARTED", Map.of("email", req.email()));
                boolean connectionOk = googlePort.testEmailConnection();
                boolean emailExists  = googlePort.emailExists(req.email());
                jobStore.step(jobId, "EMAIL_CHECK", "SUCCESS",
                        Map.of("connectionOk", connectionOk, "emailExists", emailExists));
                notifier.notify(jobId);
            }

            jobStore.finish(jobId, "SUCCESS", null);
            notifier.notify(jobId);
            return JobResult.success(jobId);

        } catch (Exception e) {
            jobStore.finish(jobId, "FAILED", e.getMessage());
            notifier.notify(jobId);
            return JobResult.failed(jobId, e.getMessage());
        }
    }

    private void safeStep(UUID jobId, String step, Runnable r) {
        try { r.run(); }
        catch (Exception e) { jobStore.step(jobId, step, "ERROR", Map.of("error", e.getMessage())); }
    }

    private String resolveUsername(ProvisioningRequest req) {
        if (req.username() != null && !req.username().isBlank()) return req.username();
        int limit = Optional.ofNullable(req.usernameLimit()).orElse(1000);
        String fullName = Optional.ofNullable(req.fullName())
                .filter(s -> !s.isBlank())
                .orElseGet(() -> ((req.firstName()==null?"":req.firstName()) + " " +
                        (req.lastName()==null?"":req.lastName())).trim());
        return ldapPort.firstAvailableUsername(fullName, limit);
    }

    private LdapCreateUserCommand toLdapCreateCommand(ProvisioningRequest req, String username) {
        return LdapCreateUserCommand.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .username(username)
                .orgUnitPath(req.orgUnitPath())
                .floor(req.floor())
                .gestor(req.gestor())
                .password(req.password())
                .build();
    }

    // record permanece igual
    public record JobResult(UUID jobId, String status, String error) {
        public static JobResult success(UUID id){ return new JobResult(id,"SUCCESS",null); }
        public static JobResult failed(UUID id,String err){ return new JobResult(id,"FAILED",err); }
    }
}
