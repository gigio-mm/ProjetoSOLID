package com.example.service;

import com.example.model.Dinheiro;
import com.example.model.Pedido;
import com.example.model.ResultadoDoPagamento;

public interface IGatewayDePagamento {
    ResultadoDoPagamento processarPagamento(Pedido pedido, Dinheiro valor);
}

