package br.com.zupacademy.vinicius.mercadolivre.compra.transacao;

import br.com.zupacademy.vinicius.mercadolivre.compra.Compra;
import br.com.zupacademy.vinicius.mercadolivre.compra.CompraRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class TransacaoForm {

    @NotBlank
    private String codigoGateway;

    @NotBlank
    private String statusTransacaoEnum;

    public Transacao toModel(CompraRepository compraRepository, Long id) {
        Optional<Compra> compra = compraRepository.findById(id);
        if (compra.isEmpty()) throw new IllegalArgumentException("Não existe uma compra com esse id.");
        return new Transacao(this.codigoGateway, compra.get(),
                TransacaoStatusEnum.montarEnum(this.statusTransacaoEnum.toUpperCase()));
    }

    public String getCodigoGateway() {
        return codigoGateway;
    }

    public String getStatusTransacaoEnum() {
        return statusTransacaoEnum;
    }

}
