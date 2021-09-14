package br.com.zupacademy.vinicius.mercadolivre.compra.gateways;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-externa")
public class GatewayController {

    @PostMapping("/notasfiscais")
    public String conectaGatewayNF(@RequestBody FormSistemaNotaFiscal form) {
        System.out.println(form.toString());
        return form.toString();
    }

    @PostMapping("/rankings")
    public String conectaGatewayRankings(@RequestBody FormSistemaRanking form){
        System.out.println(form.toString());
        return form.toString();
    }

}
