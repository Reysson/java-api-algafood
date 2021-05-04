package com.algaworks.algafoodreysson.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CidadeDtoInput {
    
    @NotBlank
    String nome;
    
    @Valid
    @NotNull
    EstadoIdInputDto estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoIdInputDto getEstado() {
        return estado;
    }

    public void setEstado(EstadoIdInputDto estadoIdInputDto) {
        this.estado = estadoIdInputDto;
    }
    
    
}
