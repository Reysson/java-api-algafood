package com.algaworks.algafoodreysson.domain.exceptions;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
    
    public UsuarioNaoEncontradoException(Integer id) {
        this(String.format("Não existe cadastro de Usuário com código %d",id));
    }
    
    
}
