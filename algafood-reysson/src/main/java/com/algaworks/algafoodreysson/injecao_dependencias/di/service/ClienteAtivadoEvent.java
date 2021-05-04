package com.algaworks.algafoodreysson.di.service;

import com.algaworks.algafoodreysson.di.modelo.Cliente;

public class ClienteAtivadoEvent {
    
    
    private Cliente cliente;


    public ClienteAtivadoEvent(Cliente cliente) {
        super();
        this.cliente = cliente;
    }


    public Cliente getCliente() {
        return this.cliente;
    }

}
