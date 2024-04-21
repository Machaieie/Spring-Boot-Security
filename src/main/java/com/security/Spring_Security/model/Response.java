package com.security.Spring_Security.model;

import java.util.Set;

import com.security.Spring_Security.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Response {
    private String firstName;
    private String lastName;
    private String username;
    private Set<UserRole> roles;
    private String token;
}
