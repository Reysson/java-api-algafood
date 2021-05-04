package com.algaworks.algafoodreysson.domain.exceptions;

public class FormaPagamentoNaoEncontradaExeception extends EntidadeNaoEncontradaException{

    public FormaPagamentoNaoEncontradaExeception(String message) {
        super(message);
    }
    
    public FormaPagamentoNaoEncontradaExeception(Integer idPagamento) {
        this(String.format("Não existe um cadastro de formaPagamento com código %d",idPagamento));
    }
    
}
