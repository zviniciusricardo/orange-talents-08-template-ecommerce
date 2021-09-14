package br.com.zupacademy.vinicius.mercadolivre.compra.transacao;

public enum TransacaoStatusEnum {
    FALHA, SUCESSO;

    public static TransacaoStatusEnum montarEnum(String value) {
        switch (value) {
            case "SUCESSO":
                return TransacaoStatusEnum.SUCESSO;
            case "1":
                return TransacaoStatusEnum.SUCESSO;
            case "FALHA":
                return TransacaoStatusEnum.FALHA;
            case "0":
                return TransacaoStatusEnum.FALHA;
        }
        throw new IllegalArgumentException("Você precisa passar um status válido");
    }
}
