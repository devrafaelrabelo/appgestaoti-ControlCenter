package com.controlcenter.entity.security;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_status", schema = "security") // ajuste o schema conforme seu banco
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatus {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String name; // substitui "status"

    @Column
    private String description;

    @Column(name = "is_active")
    private boolean active;
}
