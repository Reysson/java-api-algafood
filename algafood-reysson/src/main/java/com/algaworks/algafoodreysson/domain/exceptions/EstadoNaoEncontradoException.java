package com.algaworks.algafoodreysson.domain.exceptions;


public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    
    private static final long serialVersionUID = 1L;
    
    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Integer estadoId) {
        this(String.format("Não existe um cadastro de estado com código %d", estadoId));
    }
}
