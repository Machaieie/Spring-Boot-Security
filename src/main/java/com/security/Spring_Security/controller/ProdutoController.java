package com.security.Spring_Security.controller;

import com.security.Spring_Security.model.Produto;
import com.security.Spring_Security.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarProduto(@RequestBody Produto produto) {
        produtoService.adicionarProduto(produto);
        return new ResponseEntity<>("Produto adicionado com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> procurarProduto(@PathVariable long id) {
        Produto produto = produtoService.procurarProduto(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable long id, @RequestBody Produto produto) {
        Produto updatedProduto = produtoService.atualizarProduto(produto);
        return new ResponseEntity<>(updatedProduto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable long id) {
        Produto produto = produtoService.procurarProduto(id);
        produtoService.exluirProduto(produto);
        return new ResponseEntity<>("Produto excluído com sucesso", HttpStatus.OK);
    }
}
