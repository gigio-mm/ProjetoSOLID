package com.example.ecommerce.model;

public class ItemPedido {
    private final String nomeProduto;
    private final int quantidade;
    private final double precoUnitario;

    public ItemPedido(Produto produto, int quantidade) {
        this.nomeProduto = produto.getNome();
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    public double calcularSubtotal() {
        return this.precoUnitario * this.quantidade;
    }
}