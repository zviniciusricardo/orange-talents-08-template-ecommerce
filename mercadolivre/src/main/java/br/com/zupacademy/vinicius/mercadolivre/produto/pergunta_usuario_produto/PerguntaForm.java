package br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto;

import br.com.zupacademy.vinicius.mercadolivre.produto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class PerguntaForm {

    @NotBlank
    private String titulo;

    public Pergunta toModel(Long id, ProdutoRepository repository, Usuario usuario) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isEmpty()) throw new IllegalArgumentException("Não existe um produto com o id "+id+".");
        return new Pergunta(this.titulo, usuario);
    }

    public String getTitulo() {
        return titulo;
    }
}
