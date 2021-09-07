package br.com.zupacademy.vinicius.mercadolivre.usuario;

import br.com.zupacademy.vinicius.mercadolivre.validator.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @NotBlank @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;

    @NotBlank @Size(min = 6, max = 64)
    @Pattern(regexp = "[a-zA-Z0-9!@#%]+")
    private String senha;

    public UsuarioForm( @NotBlank String login,
                        @NotBlank String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, this.senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
