package com.example.service;

import com.example.model.Pedido;

public interface IReservadorDeEstoque {
    boolean reservar(Pedido pedido);
    void liberarReserva(Pedido pedido);
}


