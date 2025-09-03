package com.example.service.impl;

import com.example.model.Pedido;
import com.example.service.IReservadorDeEstoque;

import java.util.HashMap;
import java.util.Map;

public class ReservadorDeEstoqueSimples implements IReservadorDeEstoque {
    // Simula um estoque disponível
    private Map<String, Integer> estoqueDisponivel = new HashMap<>();

    public ReservadorDeEstoqueSimples() {
        // Popula com alguns dados de exemplo
        estoqueDisponivel.put("SKU001", 100);
        estoqueDisponivel.put("SKU002", 50);
    }

    @Override
    public boolean reservar(Pedido pedido) {
        System.out.println("Tentando reservar estoque para o pedido: " + pedido.getId());
        // Lógica de reserva simplificada
        for (com.example.model.Item item : pedido.getItens()) {
            if (estoqueDisponivel.getOrDefault(item.getSku(), 0) < item.getQuantidade()) {
                System.out.println("Estoque insuficiente para " + item.getSku());
                return false;
            }
        }
        // Se tudo ok, "reserva" o estoque (apenas simulação)
        for (com.example.model.Item item : pedido.getItens()) {
            estoqueDisponivel.put(item.getSku(), estoqueDisponivel.get(item.getSku()) - item.getQuantidade());
        }
        System.out.println("Estoque reservado com sucesso para o pedido: " + pedido.getId());
        return true;
    }

    @Override
    public void liberarReserva(Pedido pedido) {
        System.out.println("Liberando reserva de estoque para o pedido: " + pedido.getId());
        // Lógica de liberação simplificada
        for (com.example.model.Item item : pedido.getItens()) {
            estoqueDisponivel.put(item.getSku(), estoqueDisponivel.get(item.getSku()) + item.getQuantidade());
        }
    }
}


