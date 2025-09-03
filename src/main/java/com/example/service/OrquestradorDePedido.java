package com.example.service;

import com.example.model.Pedido;
import com.example.model.StatusDoPedido;

public class OrquestradorDePedido {
    private final ServicoDePedido servicoDePedido;
    private final IRepositorioDePedidos repositorioDePedidos;

    public OrquestradorDePedido(ServicoDePedido servicoDePedido, IRepositorioDePedidos repositorioDePedidos) {
        this.servicoDePedido = servicoDePedido;
        this.repositorioDePedidos = repositorioDePedidos;
    }

    public void iniciarProcessamento(String idPedido) {
        System.out.println("\n--- Orquestrador: Iniciando processamento para o pedido: " + idPedido + " ---");
        Pedido pedido = repositorioDePedidos.buscarPorId(idPedido);

        if (pedido == null) {
            System.out.println("Orquestrador: Pedido " + idPedido + " não encontrado.");
            return;
        }

        if (servicoDePedido.processarPedido(pedido)) {
            System.out.println("Orquestrador: Processamento do pedido " + idPedido + " concluído com sucesso.");
            // Em um sistema real, aqui publicaria um evento de sucesso
        } else {
            System.out.println("Orquestrador: Processamento do pedido " + idPedido + " falhou. Iniciando compensação...");
            compensar(idPedido);
        }
    }

    public void compensar(String idPedido) {
        System.out.println("Orquestrador: Compensando ações para o pedido: " + idPedido);
        // A lógica de compensação já está parcialmente no ServicoDePedido.cancelarPedido
        // Aqui, o orquestrador garantiria que todas as etapas sejam revertidas
        servicoDePedido.cancelarPedido(idPedido);
        System.out.println("Orquestrador: Compensação para o pedido " + idPedido + " concluída.");
        // Em um sistema real, aqui publicaria um evento de falha/compensação
    }
}


