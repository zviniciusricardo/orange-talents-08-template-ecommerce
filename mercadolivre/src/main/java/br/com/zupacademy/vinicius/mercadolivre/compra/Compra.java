package br.com.zupacademy.vinicius.mercadolivre.compra;

import br.com.zupacademy.vinicius.mercadolivre.produto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull
    private Integer quantidade;

    @Enumerated
    @NotNull
    private GatewayEnum gateway;

    private StatusCompraEnum status;

    private Double precoProduto;

    @ManyToOne
    @NotNull
    private Produto produto;

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @Deprecated
    public Compra() {
    }

    public Compra(@Positive @NotNull Integer quantidade, @NotNull GatewayEnum gateway,
                  @NotNull Produto produto, @NotNull Usuario usuario) {
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.produto = produto;
        this.usuario = usuario;
        this.status = StatusCompraEnum.INICIADA;
        this.precoProduto = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayEnum getGateway() {
        return gateway;
    }

    public StatusCompraEnum getStatus() {
        return status;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", gateway=" + gateway +
                ", status=" + status +
                ", valorProdutoMomentoCompra=" + precoProduto +
                ", produto=" + produto +
                ", usuario=" + usuario +
                '}';
    }
}

