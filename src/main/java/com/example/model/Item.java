package com.example.model;

public class Item {
    private String sku;
    private int quantidade;
    private Dinheiro precoUnitario;

    public Item(String sku, int quantidade, Dinheiro precoUnitario) {
        this.sku = sku;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // Getters (métodos omitidos para brevidade)
    public String getSku() {
        return sku;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Dinheiro getPrecoUnitario() {
        return precoUnitario;
    }
}