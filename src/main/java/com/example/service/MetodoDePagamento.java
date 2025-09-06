package com.example.service;
import com.example.model.ResultadoDoPagamento;

public interface MetodoDePagamento {
    ResultadoDoPagamento pagar(double valor);

}