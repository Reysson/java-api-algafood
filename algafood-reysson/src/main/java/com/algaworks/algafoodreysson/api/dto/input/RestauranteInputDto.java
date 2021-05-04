package com.algaworks.algafoodreysson.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class RestauranteInputDto {
    
    
    @NotBlank
    private String nome;
    
    @NotNull
    @PositiveOrZero
    private Float taxaFrete;
    
    @Valid
    @NotNull
    private CozinhaIdInputDto cozinha;
    
    @Valid
    @NotNull
    private EnderecoInputDto endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(Float taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public CozinhaIdInputDto getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaIdInputDto cozinha) {
        this.cozinha = cozinha;
    }

    public EnderecoInputDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInputDto endereco) {
        this.endereco = endereco;
    }
    
    
    
    
}
