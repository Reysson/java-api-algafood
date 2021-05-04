package com.algaworks.algafoodreysson.api.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioInpuDto {

    @NotBlank
    private String nome;
    
    @Email
    @NotBlank
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
