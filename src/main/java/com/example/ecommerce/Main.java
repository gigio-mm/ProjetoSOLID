package com.example.ecommerce;

import com.example.ecommerce.model.*;
import com.example.ecommerce.notification.IServicoDeNotificacao;
import com.example.ecommerce.notification.impl.ServicoDeEmail;
import com.example.ecommerce.payment.MetodoDePagamento;
import com.example.ecommerce.payment.impl.PagamentoCartaoCredito;
import com.example.ecommerce.payment.impl.PagamentoPix;
import com.example.ecommerce.repository.IPedidoRepository;
import com.example.ecommerce.repository.impl.PedidoRepositoryImpl;
import com.example.ecommerce.service.PedidoService;

public class Main {
    public static void main(String[] args) {
        // --- 1. CONFIGURAÇÃO (INJEÇÃO DE DEPENDÊNCIA MANUAL) ---
        IPedidoRepository repositorio = new PedidoRepositoryImpl();
        IServicoDeNotificacao notificacao = new ServicoDeEmail();
        MetodoDePagamento pagamentoComCartao = new PagamentoCartaoCredito();
        MetodoDePagamento pagamentoComPix = new PagamentoPix();

        PedidoService pedidoService = new PedidoService(repositorio, notificacao);

        // --- 2. SIMULAÇÃO DE COMPRA ---
        Cliente cliente = new Cliente("cli-123", "Gigio");
        Produto notebook = new ProdutoFisico("Notebook Gamer", 4500.00, 2.5);
        Produto ebook = new ProdutoDigital("SOLID para Ninjas", 99.00, "http://download.solid");

        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionarItem(notebook, 1);
        carrinho.adicionarItem(ebook, 1);

        // Processa o pedido com Cartão de Crédito
        pedidoService.criarEProcessarPedido(cliente, carrinho, pagamentoComCartao);
    }
}