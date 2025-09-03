package com.example.model;

public class Dinheiro {
    private double valor;
    private String moeda;

    public Dinheiro(double valor, String moeda) {
        this.valor = valor;
        this.moeda = moeda;
    }

    // Getters (métodos omitidos para brevidade, mas seriam incluídos)
    public double getValor() {
        return valor;
    }

    public String getMoeda() {
        return moeda;
    }
}


