package br.com.zupacademy.vinicius.mercadolivre.produto;

import br.com.zupacademy.vinicius.mercadolivre.categoria.Categoria;
import br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto.Caracteristica;
import br.com.zupacademy.vinicius.mercadolivre.produto.imagem_produto.Imagem;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_produto.Opiniao;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    @Positive
    private Integer estoque;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @NotNull
    private Categoria categoria;

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @Size(min = 3)
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Caracteristica> caracteristicas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opiniao> opinioes;


    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @NotNull Double valor,
                   @NotNull Integer estoque,
                   @NotBlank String descricao, @NotNull Categoria categoria,
                   @NotNull List<Caracteristica> caracteristicas,
                   @NotNull Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void adicionarImagens(List<Imagem> imagens) {
        imagens.forEach(imagem -> {
            if (!this.imagens.contains(imagem)) {
                this.imagens.add(imagem);
            }
        });
    }

    public void adicionarOpiniao(Opiniao opiniao) {
        this.opinioes.add(opiniao);
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }
}

