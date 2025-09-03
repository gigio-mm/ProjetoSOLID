package com.example.service.impl;

import com.example.model.Dinheiro;
import com.example.model.Pedido;
import com.example.model.ResultadoDoPagamento;
import com.example.service.IGatewayDePagamento;

public class GatewayDeCartao implements IGatewayDePagamento {
    private String apiKey;

    public GatewayDeCartao(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public ResultadoDoPagamento processarPagamento(Pedido pedido, Dinheiro valor) {
        System.out.println("Processando pagamento de " + valor.getValor() + " " + valor.getMoeda() + " para o pedido " + pedido.getId() + " via Gateway de Cartão (API Key: " + apiKey + ")");
        // Lógica de processamento de pagamento simplificada
        if (valor.getValor() > 0) {
            // Simula sucesso
            return new ResultadoDoPagamento(true, "TRANS_CARTAO_" + System.currentTimeMillis());
        } else {
            // Simula falha
            return new ResultadoDoPagamento(false, null);
        }
    }
}


