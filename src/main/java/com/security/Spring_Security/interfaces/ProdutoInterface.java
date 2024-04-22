package com.security.Spring_Security.interfaces;

import java.util.List;

import com.security.Spring_Security.model.Produto;

public interface ProdutoInterface <T>{
    void adicionarProduto(T produto);
    List<Produto> listarProdutos();
    Produto procurarProduto(long id);
    Produto atualizarProduto(T produto);
    void exluirProduto(T produto);
}
