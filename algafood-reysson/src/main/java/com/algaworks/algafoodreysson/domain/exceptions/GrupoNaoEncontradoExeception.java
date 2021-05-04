package com.algaworks.algafoodreysson.domain.exceptions;

public class GrupoNaoEncontradoExeception extends EntidadeNaoEncontradaException{

    public GrupoNaoEncontradoExeception(String message) {
        super(message);
    }
    
    public GrupoNaoEncontradoExeception(Integer id) {
        this(String.format("Não existe cadastro de grupo com o código %d",id));
    }
    
    
}
