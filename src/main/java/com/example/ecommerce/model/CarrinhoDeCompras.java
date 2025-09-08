package com.example.ecommerce.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras {
    private final Map<Produto, Integer> itens;

    public CarrinhoDeCompras() {
        this.itens = new HashMap<>();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) return;
        int quantidadeAtual = this.itens.getOrDefault(produto, 0);
        this.itens.put(produto, quantidadeAtual + quantidade);
    }

    public Map<Produto, Integer> getItens() {
        return Collections.unmodifiableMap(this.itens);
    }
}