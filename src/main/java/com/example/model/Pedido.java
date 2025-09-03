package com.example.model;

import java.util.List;

public class Pedido {
    private String id;
    private StatusDoPedido status;
    private Dinheiro total;
    private List<Item> itens;

    public Pedido(String id, StatusDoPedido status, Dinheiro total, List<Item> itens) {
        this.id = id;
        this.status = status;
        this.total = total;
        this.itens = itens;
    }

    // Getters e Setters (apenas alguns para exemplo)
    public String getId() {
        return id;
    }

    public StatusDoPedido getStatus() {
        return status;
    }

    public void setStatus(StatusDoPedido status) {
        this.status = status;
    }

    public Dinheiro getTotal() {
        return total;
    }

    public List<Item> getItens() {
        return itens;
    }
}


