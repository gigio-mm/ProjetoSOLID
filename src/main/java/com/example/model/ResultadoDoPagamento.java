package com.example.model;

public class ResultadoDoPagamento {
    private boolean sucesso;
    private String idTransacao;

    public ResultadoDoPagamento(boolean sucesso, String idTransacao) {
        this.sucesso = sucesso;
        this.idTransacao = idTransacao;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getIdTransacao() {
        return idTransacao;
    }
}