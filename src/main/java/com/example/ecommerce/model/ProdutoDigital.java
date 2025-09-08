package com.example.ecommerce.model;

public class ProdutoDigital extends Produto {
    private final String urlDownload;

    public ProdutoDigital(String nome, double preco, String urlDownload) {
        super(nome, preco);
        this.urlDownload = urlDownload;
    }

    public String getUrlDownload() { return urlDownload; }
}