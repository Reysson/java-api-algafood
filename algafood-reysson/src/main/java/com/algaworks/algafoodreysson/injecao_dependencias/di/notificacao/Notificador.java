package com.algaworks.algafoodreysson.di.notificacao;

import com.algaworks.algafoodreysson.di.modelo.Cliente;

public interface Notificador {
    
    void notificar(Cliente cliente, String mensagem);

}
