package com.controlcenter.admin.repository;

import com.controlcenter.admin.dto.ManagerUserSimpleDTO;
import com.controlcenter.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminUserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    @EntityGraph(attributePaths = {
            "status", "roles", "departments", "position"
    })
    Page<User> findAll(Specification<User> spec, Pageable pageable);


    @EntityGraph(attributePaths = {
            "status",
            "roles",
            "departments",
            "functions",
            "position",
            "currentCorporatePhones",
            "currentInternalExtensions",
            "personalPhoneNumbers",      // @ElementCollection
            "userPermissions"            // @OneToMany
    })
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findFullById(@Param("id") UUID id);

    @Query("""
        SELECT new com.controlcenter.admin.dto.ManagerUserSimpleDTO(
            u.id,
            COALESCE(u.fullName, CONCAT(u.firstName, ' ', u.lastName))
        )
        FROM User u
        JOIN u.functions f
        WHERE LOWER(f.name) LIKE %:term%
        GROUP BY u.id, u.fullName, u.firstName, u.lastName
    """)
    List<ManagerUserSimpleDTO> findAllManagerNamesByTerm(String term);
}
