package com.security.Spring_Security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.Spring_Security.model.AuthenticationResponse;
import com.security.Spring_Security.model.Usuario;
import com.security.Spring_Security.services.AuthenticationService;

@RestController 
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Usuario usuario){
        return ResponseEntity.ok(authenticationService.register(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody Usuario usuario){
        return ResponseEntity.ok(authenticationService.authenticate(usuario));
    }
}
