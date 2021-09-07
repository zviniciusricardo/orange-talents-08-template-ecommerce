package br.com.zupacademy.vinicius.mercadolivre.categoria;

public class CategoriaDto {

    private Long id;
    private String nome;
    private Long parent;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getParent() {
        return parent;
    }
}
