package com.security.Spring_Security.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.Spring_Security.dto.RegistrationRequestDTO;
import com.security.Spring_Security.exceptions.DuplicatedEntitiesExceptions;
import com.security.Spring_Security.model.AuthenticationResponse;
import com.security.Spring_Security.model.Response;
import com.security.Spring_Security.model.UserRole;
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

   public AuthenticationResponse register(RegistrationRequestDTO request) {
      Optional<Usuario> user = userRepository.findByUsername(request.username());
      if (user.isPresent()) {
         throw new DuplicatedEntitiesExceptions(
               "Usuario com o nome de " + request.username() + " ja foi cadastrado no sistema");
      }
      Usuario usuario = new Usuario();
      usuario.setFirstName(request.firstName());
      usuario.setLastName(request.lastName());
      usuario.setUsername(request.username());
      usuario.setPassword(passwordEncoder.encode(request.password()));
      usuario.addRole(new UserRole(request.role()));

      usuario = userRepository.save(usuario);
      String token = jwtService.generateToken(usuario);
      return new AuthenticationResponse(token);
   }

   public Response authenticate(Usuario request) {

      try {
         var usernamePassword = new UsernamePasswordAuthenticationToken(
               request.getUsername(),
               request.getPassword());

         var auth = this.authenticationManager.authenticate(usernamePassword);
         Usuario usuario = userRepository.findByUsername(request.getUsername()).orElseThrow();
         String token = jwtService.generateToken(usuario);
         UserDetails userDetails = (UserDetails) auth.getPrincipal();
         Response response = new Response();
         Set<UserRole> roles = ((Usuario) auth.getPrincipal()).getRole();
         response.setFirstName(usuario.getFirstName());
         response.setLastName(usuario.getLastName());
         response.setUsername(userDetails.getUsername());
         response.setRoles(roles);
         response.setToken(token);
         return response;
      } catch (BadCredentialsException e) {
         throw new BadCredentialsException("Usuario ou senha invalido!");
      }
      

   }
}
