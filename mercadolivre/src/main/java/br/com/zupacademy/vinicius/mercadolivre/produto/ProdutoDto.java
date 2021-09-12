package br.com.zupacademy.vinicius.mercadolivre.produto;

import br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto.CaracteristicaDto;
import br.com.zupacademy.vinicius.mercadolivre.produto.imagem_produto.ImagemDto;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_produto.OpiniaoDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDto {

    private Long id;
    private String nome;
    private Double valor;
    private Integer estoque;
    private String descricao;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private Long categoriaId;
    private String donoProduto;
    private List<CaracteristicaDto> caracteristicas = new ArrayList<>();
    private List<ImagemDto> imagens = new ArrayList<>();
    private List<OpiniaoDto> opinioes = new ArrayList<>();


    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.estoque = produto.getEstoque();
        this.descricao = produto.getDescricao();
        this.dataCriacao = produto.getDataCriacao();
        this.categoriaId = produto.getCategoria().getId();
        this.donoProduto = produto.getUsuario().getLogin();
        setCaracteristicaProduto(produto);
        setImagensProduto(produto);
        setOpiniaoProduto(produto);

    }

    private void setImagensProduto(Produto produto) {
        if ((produto.getImagens() != null) && (produto.getImagens().size() > 0)) {
            produto.getImagens().forEach(imagem -> {
                ImagemDto imagemDto = new ImagemDto(imagem.getEndereco());
                this.imagens.add(imagemDto);
            });
        }
    }

    private void setCaracteristicaProduto(Produto produto) {
        if ((produto.getCaracteristicas() != null) && (produto.getCaracteristicas().size() > 0)) {
            produto.getCaracteristicas().forEach(caracteristica -> {
                CaracteristicaDto caracteristicaDto = new CaracteristicaDto(caracteristica);
                this.caracteristicas.add(caracteristicaDto);
            });
        }
    }

    private void setOpiniaoProduto(Produto produto) {
        if ((produto.getOpinioes() != null) && (produto.getOpinioes().size() > 0)) {
            produto.getOpinioes().forEach(opiniao -> {
                OpiniaoDto opiniaoDto = new OpiniaoDto(opiniao);
                this.opinioes.add(opiniaoDto);
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

    public Integer getEstoque() {
        return estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
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

    public List<ImagemDto> getImagens() {
        return imagens;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }
}
