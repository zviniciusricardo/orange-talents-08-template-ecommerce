package br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_produto;

import br.com.zupacademy.vinicius.mercadolivre.produto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class OpiniaoForm {

    @NotNull
    @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    public Opiniao toModel(Long produtoId, ProdutoRepository repository, Usuario usuario) {
        Optional<Produto> produto = repository.findById(produtoId);
        if (produto.isEmpty()) throw new IllegalArgumentException("Não existe um produto com esse produtoId.");
        return new Opiniao(this.nota, this.titulo, this.descricao, usuario);
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
