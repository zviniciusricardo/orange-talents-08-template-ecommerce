package br.com.zupacademy.vinicius.mercadolivre.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm:ssss");

    public static void formataData(LocalDateTime data) {
    }
}
