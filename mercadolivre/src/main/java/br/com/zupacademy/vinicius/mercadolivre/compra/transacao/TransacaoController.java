package br.com.zupacademy.vinicius.mercadolivre.compra.transacao;

import br.com.zupacademy.vinicius.mercadolivre.compra.Compra;
import br.com.zupacademy.vinicius.mercadolivre.compra.CompraRepository;
import br.com.zupacademy.vinicius.mercadolivre.compra.gateways.Gateways;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private Gateways gateways;

    @PostMapping
    @RequestMapping("/retorno-pagseguro/{id}")
    public String processaTransacaoPagSeguro(@PathVariable("id") Long id, @RequestBody @Valid TransacaoForm form) {
        return processaTransacao(id, form);
    }

    @PostMapping
    @RequestMapping("/retorno-paypal/{id}")
    public String processaTransacaoPaypal(@PathVariable("id") Long id, @RequestBody @Valid TransacaoForm form) {
        return processaTransacao(id, form);
    }

    private String processaTransacao(Long id, TransacaoForm form) {
        Transacao transacao = form.toModel(this.compraRepository, id);
        Compra compra = compraRepository.findById(id).get();
        compra.adicionarTransacao(transacao, gateways);
        compra = compraRepository.save(compra);
        return "Transação realizada com sucesso!\nAbaixo, detalhes da compra:\n " + "\t"+ compra;
    }
}
