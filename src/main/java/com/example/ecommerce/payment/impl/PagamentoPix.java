package com.example.ecommerce.payment.impl;
import com.example.ecommerce.payment.MetodoDePagamento;

public class PagamentoPix implements MetodoDePagamento {
    @Override
    public boolean pagar(double valor) {
        System.out.println("[PAGAMENTO] Pagando R$" + valor + " com PIX...");
        return true;
    }
}