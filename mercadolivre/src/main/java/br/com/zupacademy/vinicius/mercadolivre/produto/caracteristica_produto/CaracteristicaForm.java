package br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaForm {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public Caracteristica toModel() {
        return new Caracteristica(this.nome, this.descricao);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
