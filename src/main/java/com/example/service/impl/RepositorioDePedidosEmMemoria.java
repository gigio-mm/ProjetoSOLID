package com.example.service.impl;

import com.example.model.Pedido;
import com.example.service.IRepositorioDePedidos;

import java.util.HashMap;
import java.util.Map;

public class RepositorioDePedidosEmMemoria implements IRepositorioDePedidos {
    private Map<String, Pedido> bancoDeDadosFalso = new HashMap<>();

    @Override
    public void salvar(Pedido pedido) {
        System.out.println("Salvando pedido " + pedido.getId() + " no repositório em memória.");
        bancoDeDadosFalso.put(pedido.getId(), pedido);
    }

    @Override
    public Pedido buscarPorId(String id) {
        System.out.println("Buscando pedido " + id + " no repositório em memória.");
        return bancoDeDadosFalso.get(id);
    }
}