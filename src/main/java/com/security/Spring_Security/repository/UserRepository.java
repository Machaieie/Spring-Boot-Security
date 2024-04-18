package com.security.Spring_Security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.Spring_Security.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario,Integer>{

    public Optional<Usuario> findByUsernameAndEnabled(String username, boolean enabled);
	public Optional<Usuario> findByUsernameAndPassword(String username, String password);
	public UserDetails findByUsername(String username);
    
}
