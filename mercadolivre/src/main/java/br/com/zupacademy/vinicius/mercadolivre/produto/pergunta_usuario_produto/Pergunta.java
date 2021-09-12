package br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto;

import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @Deprecated
    public Pergunta() {}

    public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario) {
        this.titulo = titulo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
