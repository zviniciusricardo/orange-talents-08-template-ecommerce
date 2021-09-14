package br.com.zupacademy.vinicius.mercadolivre.compra.gateways;

import javax.validation.constraints.NotNull;

public class FormSistemaNotaFiscal {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public FormSistemaNotaFiscal(@NotNull Long idCompra, @NotNull Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    @Override
    public String toString() {
        return "FormSistemaNotaFiscal{" +
                "idCompra=" + idCompra +
                ", idUsuario=" + idComprador +
                '}';
    }
}
