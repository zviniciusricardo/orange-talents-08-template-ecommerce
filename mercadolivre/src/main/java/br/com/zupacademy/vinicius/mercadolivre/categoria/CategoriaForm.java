package br.com.zupacademy.vinicius.mercadolivre.categoria;

import br.com.zupacademy.vinicius.mercadolivre.validator.UniqueValue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaForm {

    @Autowired
    CategoriaRepository repository;

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    private Long categoriaMaeId;

    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public CategoriaForm() {
    }

    public Categoria toModel(CategoriaRepository repository) {
        if(categoriaMaeId == null) {
            return new Categoria(this.nome, null);
        }
        Optional<Categoria> temCategoriaMae = repository.findById(this.categoriaMaeId);
        return temCategoriaMae.map(categoria ->
                new Categoria(this.nome, categoria)).orElseGet(() ->
                new Categoria(this.nome, temCategoriaMae.get()));
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    public void setCategoriaMaeId(Long categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }

}
