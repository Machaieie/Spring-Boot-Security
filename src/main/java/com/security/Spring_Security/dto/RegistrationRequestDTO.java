package com.security.Spring_Security.dto;

import com.security.Spring_Security.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequestDTO(
@NotBlank String firstName,
@NotBlank String lastName,
@NotBlank String username,
@NotBlank String password,
@NotNull Role role
) {
    
}
