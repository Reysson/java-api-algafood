package com.algaworks.algafoodreysson.api.dto;

/**
 *
 * Quando for alterar os nomes dos atributos alterar os nomes Getters e Setters
 * se não, não funciona. Algumas alterações de nomes devem ser testadas, pois
 * podem retornar null. Isso se dá por que o Mapper as vezes não consegue
 * interpretar essa alteração com o modelo real.
 *
 */
public class RestauranteDto {

    private Integer id;
    private String nome;
    private Float taxaFrete;

    private CozinhaDto cozinha;

    private EnderecoDto endereco;

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

    public Float getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(Float frete) {
        this.taxaFrete = frete;
    }

    public CozinhaDto getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaDto cozinha) {
        this.cozinha = cozinha;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

}
