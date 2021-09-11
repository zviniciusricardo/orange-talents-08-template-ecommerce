package br.com.zupacademy.vinicius.mercadolivre.produto;

import br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto.CaracteristicaDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDto {

    private Long id;
    private String nome;
    private Double valor;
    private Integer quantidadeDisponivel;
    private String descricao;
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    private Long categoriaId;
    private String donoProduto;
    private List<CaracteristicaDto> caracteristicas = new ArrayList<>();

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.descricao = produto.getDescricao();
        this.instanteCadastro = produto.getInstanteCadastro();
        this.categoriaId = produto.getCategoria().getId();
        this.donoProduto = produto.getUsuario().getLogin();
        preencherCaracteristicas(produto);
    }

    private void preencherCaracteristicas(Produto produto) {
        if ((produto.getCaracteristicas() != null) && (produto.getCaracteristicas().size() > 0)) {
            produto.getCaracteristicas().forEach(caracteristica -> {
                CaracteristicaDto caracteristicaDto = new CaracteristicaDto(caracteristica);
                this.caracteristicas.add(caracteristicaDto);
            });
        }
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

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getDonoProduto() {
        return donoProduto;
    }

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }
}
