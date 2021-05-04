package com.algaworks.algafoodreysson.api.dto.input;

import javax.validation.constraints.NotBlank;

public class CozinhaInputDto {
    
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
