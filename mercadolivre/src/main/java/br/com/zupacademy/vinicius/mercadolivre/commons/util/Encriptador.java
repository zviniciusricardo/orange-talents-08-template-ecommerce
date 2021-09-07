package br.com.zupacademy.vinicius.mercadolivre.commons.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Encriptador {

    public static String hashPassword(String senhaLimpa) {
        return BCrypt.hashpw(senhaLimpa, BCrypt.gensalt());
    }

    public static Boolean checkPass(String plainPassword, String hashedPassword) throws IllegalArgumentException {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else throw new IllegalArgumentException("As senhas não batem");
    }
}
