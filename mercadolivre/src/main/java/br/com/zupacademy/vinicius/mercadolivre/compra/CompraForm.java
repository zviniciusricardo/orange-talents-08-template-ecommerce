package br.com.zupacademy.vinicius.mercadolivre.compra;

import br.com.zupacademy.vinicius.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {

    @NotNull
    private Long produtoId;

    @Positive @NotNull
    private Integer quantidade;

    @NotNull
    private GatewayEnum gateway;

    public Compra toModel(ProdutoRepository produtoRepository, Usuario usuario) {
        if (!produtoRepository.existsById(produtoId)) throw
                new IllegalArgumentException("O recurso não existe ou tem um id diferente");
        return new Compra(this.quantidade, this.gateway,
                produtoRepository.findById(produtoId).get(), usuario);
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayEnum getGateway() {
        return gateway;
    }
}

