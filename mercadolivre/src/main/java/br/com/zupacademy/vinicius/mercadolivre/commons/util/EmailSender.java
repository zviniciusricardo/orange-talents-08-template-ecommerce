package br.com.zupacademy.vinicius.mercadolivre.commons.util;

import br.com.zupacademy.vinicius.mercadolivre.compra.Compra;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

public class EmailSender {

    public static void sendMail(Usuario donoProduto, Usuario donoPergunta) {
        System.out.println("Olá, " + donoProduto.getLogin() + ". O usuário " +
                donoPergunta.getLogin() + " fez uma pergunta no seu anúncio");
    }

    public static void enviarEmailIndicacaoCompra(Usuario usuarioDono, Usuario usuarioPergunta, Compra compra) {
        System.out.println("Olá, " + usuarioDono.getLogin() + ". O usuário " + usuarioPergunta.getLogin() + " quer comprar " +
                compra.getQuantidade() + " unidades do seu produto " + compra.getProduto().getNome() +
                ". Contate-o o mais rápido possível para não perder essa oportunidade de fazer negócio.");
    }
}
