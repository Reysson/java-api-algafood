package com.algaworks.algafoodreysson.api.dto;

public class CidadeDto {
    
    private Integer id;
    private String nome;
    private EstadoDto estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoDto getEstado() {
        return estado;
    }

    public void setEstado(EstadoDto estado) {
        this.estado = estado;
    }
    
    
    
    
    
}