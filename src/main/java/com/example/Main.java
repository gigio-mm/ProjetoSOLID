package com.example;

import com.example.model.Dinheiro;
import com.example.model.Item;
import com.example.model.Pedido;
import com.example.model.StatusDoPedido;
import com.example.service.IGatewayDePagamento;
import com.example.service.IReservadorDeEstoque;
import com.example.service.IRepositorioDePedidos;
import com.example.service.OrquestradorDePedido;
import com.example.service.ServicoDePedido;
import com.example.service.impl.GatewayDeCartao;
import com.example.service.impl.RepositorioDePedidosEmMemoria;
import com.example.service.impl.ReservadorDeEstoqueSimples;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Configuração das dependências (Injeção de Dependência manual)
        IReservadorDeEstoque reservadorDeEstoque = new ReservadorDeEstoqueSimples();
        IGatewayDePagamento gatewayDePagamento = new GatewayDeCartao("minha-api-key-secreta");
        IRepositorioDePedidos repositorioDePedidos = new RepositorioDePedidosEmMemoria();

        ServicoDePedido servicoDePedido = new ServicoDePedido(
                reservadorDeEstoque,
                gatewayDePagamento,
                repositorioDePedidos
        );

        OrquestradorDePedido orquestradorDePedido = new OrquestradorDePedido(
                servicoDePedido,
                repositorioDePedidos
        );

        // 2. Criando um pedido de exemplo
        Item item1 = new Item("SKU001", 2, new Dinheiro(10.0, "BRL"));
        Item item2 = new Item("SKU002", 1, new Dinheiro(25.0, "BRL"));
        List<Item> itensPedido1 = Arrays.asList(item1, item2);
        Dinheiro totalPedido1 = new Dinheiro(45.0, "BRL"); // 2*10 + 1*25 = 45

        Pedido pedido1 = new Pedido("PEDIDO001", StatusDoPedido.PENDENTE, totalPedido1, itensPedido1);
        repositorioDePedidos.salvar(pedido1); // Salva o pedido inicial no repositório

        // 3. Processando o pedido via orquestrador
        orquestradorDePedido.iniciarProcessamento(pedido1.getId());

        // 4. Verificando o status final do pedido
        Pedido pedidoFinal = repositorioDePedidos.buscarPorId(pedido1.getId());
        System.out.println("\nStatus final do Pedido " + pedidoFinal.getId() + ": " + pedidoFinal.getStatus());

        // --- Exemplo de um pedido que falharia no pagamento (para testar compensação) ---
        Item item3 = new Item("SKU001", 1, new Dinheiro(5.0, "BRL"));
        List<Item> itensPedido2 = Arrays.asList(item3);
        Dinheiro totalPedido2 = new Dinheiro(-10.0, "BRL"); // Valor negativo para simular falha no pagamento

        Pedido pedido2 = new Pedido("PEDIDO002", StatusDoPedido.PENDENTE, totalPedido2, itensPedido2);
        repositorioDePedidos.salvar(pedido2);

        orquestradorDePedido.iniciarProcessamento(pedido2.getId());

        Pedido pedidoFalho = repositorioDePedidos.buscarPorId(pedido2.getId());
        System.out.println("\nStatus final do Pedido (falho) " + pedidoFalho.getId() + ": " + pedidoFalho.getStatus());
    }
}


