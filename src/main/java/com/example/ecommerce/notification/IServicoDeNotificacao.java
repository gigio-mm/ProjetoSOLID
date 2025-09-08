package com.example.ecommerce.notification;
import com.example.ecommerce.model.Pedido;

public interface IServicoDeNotificacao {
    void enviarConfirmacao(Pedido pedido);
}