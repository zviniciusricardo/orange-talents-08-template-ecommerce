package br.com.zupacademy.vinicius.mercadolivre.categoria;

public class CategoriaDto {

    private Long id;
    private String nome;
    private Categoria categoriaMae;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoriaMae = categoria.getCategoriaMae();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
