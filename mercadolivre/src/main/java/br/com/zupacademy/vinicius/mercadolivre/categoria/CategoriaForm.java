package br.com.zupacademy.vinicius.mercadolivre.categoria;

import br.com.zupacademy.vinicius.mercadolivre.validator.UniqueValue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @Autowired
    CategoriaRepository repository;

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    private Long parent;

    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
}
