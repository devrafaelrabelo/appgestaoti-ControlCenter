package com.controlcenter.user.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank
    private String cpf;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String fullName;

    @NotBlank
    private String phone;

    @NotBlank
    private String supervisorId;

    private String leaderId;

    @NotBlank
    private String cep;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String neighborhood;

    private String complement;

    @NotBlank
    private String city;

    @NotBlank
    private String state;
}
