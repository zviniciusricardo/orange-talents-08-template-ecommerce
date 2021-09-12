package br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
