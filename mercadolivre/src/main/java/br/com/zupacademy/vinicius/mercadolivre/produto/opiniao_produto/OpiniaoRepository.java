package br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
}
