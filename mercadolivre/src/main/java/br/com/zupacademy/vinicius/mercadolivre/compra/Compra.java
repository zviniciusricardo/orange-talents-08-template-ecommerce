package br.com.zupacademy.vinicius.mercadolivre.compra;

import br.com.zupacademy.vinicius.mercadolivre.commons.util.EmailSender;
import br.com.zupacademy.vinicius.mercadolivre.compra.gateways.Gateways;
import br.com.zupacademy.vinicius.mercadolivre.compra.gateways.FormSistemaNotaFiscal;
import br.com.zupacademy.vinicius.mercadolivre.compra.gateways.FormSistemaRanking;
import br.com.zupacademy.vinicius.mercadolivre.compra.transacao.TransacaoStatusEnum;
import br.com.zupacademy.vinicius.mercadolivre.compra.transacao.Transacao;
import br.com.zupacademy.vinicius.mercadolivre.produto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private List<Transacao> transacoes = new ArrayList<>();

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

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void adicionarTransacao(Transacao transacao, Gateways gateways) {
        if (this.transacoes.contains(transacao)) {
            throw new IllegalArgumentException("Essa transação já aconteceu!");
        }
        List<Transacao> transacoesComSucesso = this.transacoes.stream()
                .filter(transacao::concluidaComSucesso).collect(Collectors.toList());

        if (!(transacoesComSucesso.size() < 2)) throw new IllegalArgumentException
                ("Você já fez o número de transações máximas para esta compra. Obrigado.");
        processarComunicacaoSistemasExternos(transacao, gateways);
        this.transacoes.add(transacao);
    }

    private void processarComunicacaoSistemasExternos(Transacao transacao, Gateways gateways) {
        if (transacao.getStatusCompraEnum().equals(TransacaoStatusEnum.SUCESSO)) {
            gateways.comunicaSistemaNotaFiscal(new FormSistemaNotaFiscal(this.id, this.usuario.getId()));
            gateways.comunicaSistemaRanking(new FormSistemaRanking(this.id, this.produto.getUsuario().getId()));
            EmailSender.sendMailSucessoCompra(this);
        }
        else {
            EmailSender.sendMailFalhaCompra(this);
        }
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

