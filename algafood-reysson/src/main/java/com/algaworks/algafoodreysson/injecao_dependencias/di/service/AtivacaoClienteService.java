package com.algaworks.algafoodreysson.di.service;

import com.algaworks.algafoodreysson.di.modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    
    @Autowired
    private ApplicationEventPublisher publisher;
	
	
	public void ativar(Cliente cliente) {
		cliente.ativar();

		publisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}

}
