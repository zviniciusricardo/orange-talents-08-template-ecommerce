package br.com.zupacademy.vinicius.mercadolivre.usuario;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository implements JpaRepository<Usuario, Long>{

    Optional<Usuario> findByLogin(String login);
}
