package com.security.Spring_Security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.Spring_Security.exceptions.DuplicatedEntitiesExceptions;
import com.security.Spring_Security.exceptions.ResourceNotFoundException;
import com.security.Spring_Security.interfaces.ProdutoInterface;
import com.security.Spring_Security.model.Produto;
import com.security.Spring_Security.repository.ProdutoRepository;

@Service
public class ProdutoService implements ProdutoInterface<Produto>{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void adicionarProduto(Produto produto) {
        String nomeUpperCase = produto.getNome().toUpperCase(); 
        boolean produtoExistente = produtoRepository.findByNomeIgnoreCase(nomeUpperCase)
                .isPresent();
    
        if (produtoExistente) {
            throw new DuplicatedEntitiesExceptions("Erro! Este produto ou nome já está registado no sistema");
        }
            produto.setNome(nomeUpperCase);
    
        produtoRepository.save(produto);
    }
    

    @Override
    public List<Produto> listarProdutos() {
       List<Produto> produtos = produtoRepository.findAll();
       return produtos;
    }

    @Override
    public Produto procurarProduto(long id) {
       return produtoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Produto com Id "+id+" não encontrado"));
    }

    @Override
    public Produto atualizarProduto(Produto produto) {
        Produto produtoExistente = produtoRepository.findByNomeIgnoreCase(produto.getNome()).orElseThrow(()-> new ResourceNotFoundException("Erro! O produto de nome "+produto.getNome()+" não foi encontrado no sistema") );
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoRepository.save(produtoExistente);

        return produtoExistente;

    }

    @Override
    public void exluirProduto(Produto produto) {
        Produto produtoExistente = produtoRepository.findByNomeIgnoreCase(produto.getNome()).orElseThrow(()-> new ResourceNotFoundException("Erro! O produto de nome "+produto.getNome()+" não foi encontrado no sistema") );
        produtoRepository.delete(produtoExistente);
    }



    
}
