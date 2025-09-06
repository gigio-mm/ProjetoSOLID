package com.example.service;

import com.example.model.Pedido;

public interface IRepositorioDePedidos {
    void salvar(Pedido pedido);
    Pedido buscarPorId(String id);
}

