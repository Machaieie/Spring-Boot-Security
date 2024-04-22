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
       Produto produtoExistente = produtoRepository.findByNome(produto.getNome()).orElseThrow(()-> new DuplicatedEntitiesExceptions("Erro! este produto ou nome já esta registado no sistema") );
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
        Produto produtoExistente = produtoRepository.findByNome(produto.getNome()).orElseThrow(()-> new ResourceNotFoundException("Erro! O produto não foi encontrado no sistema") );
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoRepository.save(produtoExistente);

        return produtoExistente;

    }

    @Override
    public void exluirProduto(Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exluirProduto'");
    }



    
}
