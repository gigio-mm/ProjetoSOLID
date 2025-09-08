package com.example.ecommerce.notification.impl;

import com.example.ecommerce.model.Pedido;
import com.example.ecommerce.notification.IServicoDeNotificacao;

public class ServicoDeEmail implements IServicoDeNotificacao {
    @Override
    public void enviarConfirmacao(Pedido pedido) {
        System.out.println("[NOTIFICACAO] Enviando e-mail de confirmação para o pedido: " + pedido.getId());
    }
}