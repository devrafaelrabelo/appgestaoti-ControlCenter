package com.controlcenter.user.repository;

import com.controlcenter.admin.dto.UserSimpleDTO;
import com.controlcenter.entity.common.Position;
import com.controlcenter.entity.user.User;
import com.controlcenter.user.projection.UserIdNameView;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByCpf(String cpf);
    boolean existsByPosition(Position position);
    long countByStatus_NameIgnoreCase(String name);
    long countByAccountLockedTrue();
    long countByAccountDeletionRequestedTrue();
    boolean existsByPersonalEmail(String personalEmail); // <- novo

    @Query("""
        SELECT DISTINCT 
            u.id AS id,
            COALESCE(u.fullName, CONCAT(u.firstName, ' ', u.lastName)) AS name
        FROM User u
        JOIN u.functions f
        WHERE (:departmentId IS NULL OR f.department.id = :departmentId)
          AND (:functionName IS NULL OR LOWER(f.name) = :functionName)
    """)
    List<UserIdNameView> findUsersByFunctionFilters(@Param("departmentId") UUID departmentId,
                                                    @Param("functionName") String functionName);


    @Query(""" 
    SELECT u FROM User u
        LEFT JOIN FETCH u.roles r
        LEFT JOIN FETCH r.permissions
        LEFT JOIN FETCH u.status
        WHERE u.email = :email
    """)
    Optional<User> findByEmailWithStatusAndRoles(@Param("email") String email);
    @EntityGraph(attributePaths = {
            "roles", "departments", "userGroups", "position", "functions", "userPermissions.permission"
    })
    Optional<User> findByEmail(String email);

    @Query("""
    SELECT u FROM User u
    LEFT JOIN FETCH u.roles r
    LEFT JOIN FETCH r.permissions
    LEFT JOIN FETCH u.departments d
    LEFT JOIN FETCH u.functions f
    LEFT JOIN FETCH u.position
    LEFT JOIN FETCH u.status
    LEFT JOIN FETCH u.userGroups
    LEFT JOIN FETCH u.userPermissions up
    LEFT JOIN FETCH up.permission
    WHERE u.id = :id
""")
    Optional<User> findDetailedById(@Param("id") UUID id);

    @EntityGraph(attributePaths = {
            "roles",
            "departments",
            "userGroups",
            "position",
            "functions",
            "userPermissions.permission",
            "currentCorporatePhones",
            "currentInternalExtensions"
    })
    Optional<User> findWithContactDetailsById(UUID id);

    @EntityGraph(attributePaths = {
            "roles",
            "departments",
            "functions",
            "position",
            "status",
            "currentCorporatePhones",
            "currentInternalExtensions",
            "allocationHistories.company",
            "personalPhoneNumbers",
            "status"
    })
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsernameFetchAll(@Param("username") String username);

    @EntityGraph(attributePaths = {"roles", "status"})
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> fetchUserWithRolesAndStatus(@Param("id") UUID id);

    @EntityGraph(attributePaths = {"roles", "status"})
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> fetchUserWithRolesAndStatusUsername(@Param("username") String username);


    @Query("""
    SELECT u FROM User u
    LEFT JOIN FETCH u.userPermissions up
    LEFT JOIN FETCH u.roles r
    LEFT JOIN FETCH r.permissions
    WHERE u.id = :id
""")
    Optional<User> findWithPermissions(UUID id);

    @Query("""
    SELECT u FROM User u
    JOIN FETCH u.status
    LEFT JOIN FETCH u.roles
    WHERE u.email = :email
""")
    Optional<User> findByEmailForLogin(@Param("email") String email);


    @Query("""
    SELECT u FROM User u
    LEFT JOIN FETCH u.roles r
    LEFT JOIN FETCH r.permissions
    WHERE u.id = :id
""")
    Optional<User> findByIdWithRolesAndPermissions(@Param("id") UUID id);



}
