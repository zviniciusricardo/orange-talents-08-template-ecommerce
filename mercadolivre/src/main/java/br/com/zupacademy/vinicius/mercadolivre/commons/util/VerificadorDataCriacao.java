package br.com.zupacademy.vinicius.mercadolivre.commons.util;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class VerificadorDataCriacao {

    public static void checaData(LocalDateTime dataCriacao) {
        Assert.isTrue(!dataCriacao.isAfter(LocalDateTime.now()),
                "Data inválida: Valor da data está no futuro");
    }
}
