package br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto;

import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

public class EmailSender {

    public static void sendMail(Usuario donoProduto, Usuario donoPergunta) {
        System.out.println("Olá, " + donoProduto.getLogin() + ". O usuário " +
                donoPergunta.getLogin() + " fez uma pergunta no seu anúncio");
    }
}
