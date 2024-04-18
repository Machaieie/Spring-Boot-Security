package com.security.Spring_Security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.Spring_Security.dto.RegistrationRequestDTO;
import com.security.Spring_Security.model.AuthenticationResponse;
import com.security.Spring_Security.model.Usuario;
import com.security.Spring_Security.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager; 

     public AuthenticationResponse register (Usuario request){
        Usuario usuario = new Usuario();
        usuario.setFistName(request.getFistName());
        usuario.setLastName(request.getLastName());
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole(request.getRole());

        usuario = userRepository.save(usuario);
        String token = jwtService.generateToken(usuario);
        return new AuthenticationResponse(token);
     }
    

     public AuthenticationResponse  authenticate(Usuario request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), 
                request.getPassword()
            )
        );
       Usuario usuario = userRepository.findByUsername(request.getUsername()).orElseThrow();
       String token  = jwtService.generateToken(usuario);
        return new AuthenticationResponse(token);
     }
}
