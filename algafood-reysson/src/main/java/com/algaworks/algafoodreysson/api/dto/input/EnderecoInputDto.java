package com.algaworks.algafoodreysson.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoInputDto {
    
    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInputDto cidade;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public CidadeIdInputDto getCidade() {
        return cidade;
    }

    public void setCidade(CidadeIdInputDto cidade) {
        this.cidade = cidade;
    }
    
    
}
