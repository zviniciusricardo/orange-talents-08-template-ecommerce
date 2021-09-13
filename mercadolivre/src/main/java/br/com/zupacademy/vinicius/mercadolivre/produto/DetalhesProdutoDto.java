package br.com.zupacademy.vinicius.mercadolivre.produto;

import br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto.CaracteristicaDto;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_usuario_produto.Opiniao;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_usuario_produto.OpiniaoDto;
import br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto.PerguntaDto;

import java.util.ArrayList;
import java.util.List;

public class DetalhesProdutoDto {

    private List<String> linkImagens = new ArrayList<>();
    private String nome;
    private Double preco;
    private List<CaracteristicaDto> caracteristicas = new ArrayList<>();
    private String descricao;
    private Double mediaNotas;
    private Integer totalNotas;
    private List<OpiniaoDto> opinioes = new ArrayList<>();
    private List<PerguntaDto> perguntas = new ArrayList<>();

    public DetalhesProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        setCaracteristicasProduto(produto);
        setMediaTotalENotas(produto);
        setOpinioesProduto(produto);
        setPerguntasProduto(produto);
        setLinkImagensProduto(produto);
    }

    private void setMediaTotalENotas(Produto produto) {
        int notas = produto.getOpinioes().stream().mapToInt(Opiniao::getNota).sum();
        this.mediaNotas = (double) (notas / produto.getOpinioes().size());
        this.totalNotas = produto.getOpinioes().size();
    }

    private void setPerguntasProduto(Produto produto) {
        produto.getPerguntas().forEach(pergunta -> {
            PerguntaDto perguntaDto = new PerguntaDto(pergunta);
            this.perguntas.add(perguntaDto);
        });
    }

    private void setOpinioesProduto(Produto produto) {
        produto.getOpinioes().forEach(opiniao -> {
            OpiniaoDto opiniaoDto = new OpiniaoDto(opiniao);
            this.opinioes.add(opiniaoDto);
        });
    }

    private void setCaracteristicasProduto(Produto produto) {
        produto.getCaracteristicas().forEach(caracteristica -> {
            CaracteristicaDto caracteristicaDto = new CaracteristicaDto(caracteristica);
            this.caracteristicas.add(caracteristicaDto);
        });
    }

    private void setLinkImagensProduto(Produto produto) {
        produto.getImagens().forEach(imagem -> {
            linkImagens.add("https.drive.google/" + imagem.getEndereco() + ".com");
        });
    }

    public List<String> getLinkImagens() {
        return linkImagens;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }
}
