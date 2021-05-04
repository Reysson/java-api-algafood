package com.algaworks.algafoodreysson.domain.exceptions;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }
    
    public RestauranteNaoEncontradoException(Integer restauranteId) {
        this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
    }
    
    
    
}
