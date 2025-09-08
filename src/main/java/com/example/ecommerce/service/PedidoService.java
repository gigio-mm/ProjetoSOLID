package com.example.ecommerce.service;

import com.example.ecommerce.model.CarrinhoDeCompras;
import com.example.ecommerce.model.Cliente;
import com.example.ecommerce.model.Pedido;
import com.example.ecommerce.notification.IServicoDeNotificacao;
import com.example.ecommerce.payment.MetodoDePagamento;
import com.example.ecommerce.repository.IPedidoRepository;

public class PedidoService {
    private final IPedidoRepository pedidoRepository;
    private final IServicoDeNotificacao servicoDeNotificacao;

    public PedidoService(IPedidoRepository pedidoRepository, IServicoDeNotificacao servicoDeNotificacao) {
        this.pedidoRepository = pedidoRepository;
        this.servicoDeNotificacao = servicoDeNotificacao;
    }

    public Pedido criarEProcessarPedido(Cliente cliente, CarrinhoDeCompras carrinho, MetodoDePagamento metodoDePagamento) {
        System.out.println("\n--- [SERVICO] Iniciando processamento do pedido ---");
        Pedido pedido = new Pedido(cliente, carrinho);

        boolean pagamentoSucesso = metodoDePagamento.pagar(pedido.getTotal());

        if (pagamentoSucesso) {
            pedido.setStatus("PAGO");
            System.out.println("[SERVICO] Pagamento APROVADO. Status alterado para PAGO.");
            this.servicoDeNotificacao.enviarConfirmacao(pedido);
        } else {
            pedido.setStatus("CANCELADO");
            System.out.println("[SERVICO] Pagamento REPROVADO. Status alterado para CANCELADO.");
        }

        this.pedidoRepository.salvar(pedido);
        return pedido;
    }
}