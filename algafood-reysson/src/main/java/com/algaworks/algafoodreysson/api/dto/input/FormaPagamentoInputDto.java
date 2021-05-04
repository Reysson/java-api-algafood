package com.algaworks.algafoodreysson.api.dto.input;

import javax.validation.constraints.NotBlank;

public class FormaPagamentoInputDto {
    
    @NotBlank
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
