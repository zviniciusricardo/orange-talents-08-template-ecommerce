package br.com.zupacademy.vinicius.mercadolivre.usuario;

import static br.com.zupacademy.vinicius.mercadolivre.commons.util.DateFormatter.formataData;
import br.com.zupacademy.vinicius.mercadolivre.commons.util.Encriptador;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static br.com.zupacademy.vinicius.mercadolivre.commons.util.VerificadorDataCriacao.checaData;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getId().equals(usuario.getId()) && Objects.equals(getLogin(), usuario.getLogin()) && Objects.equals(getSenha(), usuario.getSenha()) && Objects.equals(getDataCriacao(), usuario.getDataCriacao()) && Objects.equals(perfis, usuario.perfis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getSenha(), getDataCriacao(), perfis);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", perfis=" + perfis +
                '}';
    }
}
