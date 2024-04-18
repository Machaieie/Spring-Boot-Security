package com.security.Spring_Security.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistrationRequestDTO(
@NotBlank String firstName,
@NotBlank String lastName,
@NotBlank String username,
@NotBlank String password
) {
    
}
