package com.example.service;

import com.example.model.Dinheiro;
import com.example.model.Pedido;
import com.example.model.ResultadoDoPagamento;
import com.example.model.StatusDoPedido;

public class ServicoDePedido {
    private final IReservadorDeEstoque reservadorDeEstoque;
    private final IGatewayDePagamento gatewayDePagamento;
    private final IRepositorioDePedidos repositorioDePedidos;
    private final IPedidoRepository pedidoRepository;
    private final MetodoDePagamento metodoDePagamento;
    private final IServicoDeNotificacao servicoDeNotificacao;

    public ServicoDePedido(
            IReservadorDeEstoque reservadorDeEstoque,
            IGatewayDePagamento gatewayDePagamento,
            IRepositorioDePedidos repositorioDePedidos,
            IPedidoRepository pedidoRepository,
            MetodoDePagamento metodoDePagamento,
            IServicoDeNotificacao servicoDeNotificacao) {

        this.reservadorDeEstoque = reservadorDeEstoque;
        this.gatewayDePagamento = gatewayDePagamento;
        this.repositorioDePedidos = repositorioDePedidos;
        this.pedidoRepository = pedidoRepository;
        this.metodoDePagamento = metodoDePagamento;
        this.servicoDeNotificacao = servicoDeNotificacao;
    }

    public boolean processarPedido(Pedido pedido) {
        System.out.println("\n--- Iniciando processamento do pedido: " + pedido.getId() + " ---");

        // 1. Reservar estoque
        if (!reservadorDeEstoque.reservar(pedido)) {
            System.out.println("Falha ao reservar estoque para o pedido: " + pedido.getId());
            pedido.setStatus(StatusDoPedido.CANCELADO);
            repositorioDePedidos.salvar(pedido);
            return false;
        }
        pedido.setStatus(StatusDoPedido.RESERVADO);
        repositorioDePedidos.salvar(pedido);

        // 2. Processar pagamento
        ResultadoDoPagamento resultadoPagamento = gatewayDePagamento.processarPagamento(pedido, pedido.getTotal());
        if (!resultadoPagamento.isSucesso()) {
            System.out.println("Falha ao processar pagamento para o pedido: " + pedido.getId());
            reservadorDeEstoque.liberarReserva(pedido); // Compensação
            pedido.setStatus(StatusDoPedido.CANCELADO);
            repositorioDePedidos.salvar(pedido);
            return false;
        }
        pedido.setStatus(StatusDoPedido.PAGO);
        repositorioDePedidos.salvar(pedido);

        // 3. Salvar pedido final (já salvo nas etapas anteriores, mas aqui seria a confirmação final)
        System.out.println("Pedido " + pedido.getId() + " processado e pago com sucesso!");
        // Em um sistema real, aqui acionaria o serviço de entrega e notificação via eventos
        return true;
    }

    public void cancelarPedido(String idPedido) {
        Pedido pedido = repositorioDePedidos.buscarPorId(idPedido);
        if (pedido != null) {
            System.out.println("Cancelando pedido: " + idPedido);
            // Lógica de compensação para cancelamento
            if (pedido.getStatus() == StatusDoPedido.RESERVADO || pedido.getStatus() == StatusDoPedido.PAGO) {
                reservadorDeEstoque.liberarReserva(pedido);
            }
            // Em um sistema real, aqui também acionaria o estorno do pagamento se já pago
            pedido.setStatus(StatusDoPedido.CANCELADO);
            repositorioDePedidos.salvar(pedido);
        } else {
            System.out.println("Pedido " + idPedido + " não encontrado para cancelamento.");
        }
    }
}


