package br.com.zupacademy.vinicius.mercadolivre.produto;

import br.com.zupacademy.vinicius.mercadolivre.categoria.Categoria;
import br.com.zupacademy.vinicius.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto.Caracteristica;
import br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto.CaracteristicaForm;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;
import br.com.zupacademy.vinicius.mercadolivre.usuario.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoForm {

    @NotBlank
    private String nome;

    @NotNull @Positive
    private Double valor;

    @NotNull @Positive
    private Integer estoque;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    private Long categoriaId;

    @Size(min = 3)
    @NotNull
    private List<CaracteristicaForm> caracteristicas;

    public ProdutoForm(String nome, Double valor,
                       Integer estoque,
                       String descricao, Long categoriaId,
                       List<CaracteristicaForm> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas = caracteristicas;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository, Usuario usuario) {
        Optional<Categoria> categoria = categoriaRepository.findById(this.categoriaId);
        if(categoria.isEmpty()) {
            throw new IllegalArgumentException("Não existe categoria referente ao recurso informado");
        }
        List<Caracteristica> caracteristicaEntity = new ArrayList<>();
        for (CaracteristicaForm caracteristica : this.caracteristicas) {
            caracteristicaEntity.add(caracteristica.toModel());
        }
        return new Produto(this.nome, this.valor,
                this.estoque,
                this.descricao, categoria.get(),
                caracteristicaEntity, usuario);
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public List<CaracteristicaForm> getCaracteristicas() {
        return caracteristicas;
    }
}