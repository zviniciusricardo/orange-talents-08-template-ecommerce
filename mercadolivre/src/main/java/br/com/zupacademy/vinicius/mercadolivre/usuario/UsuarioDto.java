package br.com.zupacademy.vinicius.mercadolivre.usuario;

public class UsuarioDto {

    private Long id;
    private String login;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
