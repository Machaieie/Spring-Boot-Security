package com.security.Spring_Security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.Spring_Security.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    Optional<Produto> findByNome(String nome);
}
