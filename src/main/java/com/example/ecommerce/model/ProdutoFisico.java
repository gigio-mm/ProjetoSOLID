package com.example.ecommerce.model;

public class ProdutoFisico extends Produto {
    private final double peso;

    public ProdutoFisico(String nome, double preco, double peso) {
        super(nome, preco);
        this.peso = peso;
    }

    public double getPeso() { return peso; }
}