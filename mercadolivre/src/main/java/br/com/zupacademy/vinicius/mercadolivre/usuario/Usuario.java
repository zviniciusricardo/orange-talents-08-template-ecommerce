package br.com.zupacademy.vinicius.mercadolivre.usuario;

import static br.com.zupacademy.vinicius.mercadolivre.commons.util.DateFormatter.formataData;
import br.com.zupacademy.vinicius.mercadolivre.commons.util.Encriptador;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static br.com.zupacademy.vinicius.mercadolivre.commons.util.VerificadorDataCriacao.checaData;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = Encriptador.hashPassword(senha);
        this.dataCriacao = getDataCriacao();
        checaData(dataCriacao);
        formataData(dataCriacao);
    }

    @Deprecated
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
