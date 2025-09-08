package com.example.ecommerce.repository.impl;

import com.example.ecommerce.model.Pedido;
import com.example.ecommerce.repository.IPedidoRepository;
import java.util.HashMap;
import java.util.Map;

public class PedidoRepositoryImpl implements IPedidoRepository {
    private static final Map<String, Pedido> bancoDeDados = new HashMap<>();

    @Override
    public void salvar(Pedido pedido) {
        System.out.println("[REPOSITORIO] Salvando/Atualizando pedido: " + pedido.getId() + " com status " + pedido.getStatus());
        bancoDeDados.put(pedido.getId(), pedido);
    }
}