package com.example.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Pedido {
    private static long proximoId = 1;
    private final String id;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private String status;
    private final double total;

    public Pedido(Cliente cliente, CarrinhoDeCompras carrinho) {
        this.id = String.valueOf(proximoId++);
        this.cliente = cliente;
        this.status = "AGUARDANDO_PAGAMENTO";
        this.itens = new ArrayList<>();
        for (Map.Entry<Produto, Integer> entry : carrinho.getItens().entrySet()) {
            this.itens.add(new ItemPedido(entry.getKey(), entry.getValue()));
        }
        this.total = this.calcularTotal();
    }

    public double calcularTotal() {
        double soma = 0.0;
        for (ItemPedido item : this.itens) {
            soma += item.calcularSubtotal();
        }
        return soma;
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return Collections.unmodifiableList(this.itens); }
    public String getStatus() { return status; }
    public double getTotal() { return total; }
    public void setStatus(String novoStatus) { this.status = novoStatus; }
}