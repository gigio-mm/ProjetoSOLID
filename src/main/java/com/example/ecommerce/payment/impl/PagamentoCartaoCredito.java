package com.example.ecommerce.payment.impl;
import com.example.ecommerce.payment.MetodoDePagamento;

public class PagamentoCartaoCredito implements MetodoDePagamento {
    @Override
    public boolean pagar(double valor) {
        System.out.println("[PAGAMENTO] Pagando R$" + valor + " com Cartão de Crédito... APROVADO.");
        return true;
    }
}