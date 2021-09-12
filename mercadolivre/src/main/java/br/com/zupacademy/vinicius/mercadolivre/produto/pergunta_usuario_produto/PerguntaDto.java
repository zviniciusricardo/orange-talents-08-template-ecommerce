package br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto;

import java.time.LocalDateTime;

public class PerguntaDto {

    private Long id;
    private String usuario;
    private String titulo;
    private LocalDateTime instanteCriacao;

    public PerguntaDto(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.usuario = pergunta.getUsuario().getLogin();
        this.titulo = pergunta.getTitulo();
        this.instanteCriacao = pergunta.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }
}
