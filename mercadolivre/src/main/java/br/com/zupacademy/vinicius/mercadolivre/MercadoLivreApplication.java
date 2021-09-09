package br.com.zupacademy.vinicius.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MercadoLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}
}
