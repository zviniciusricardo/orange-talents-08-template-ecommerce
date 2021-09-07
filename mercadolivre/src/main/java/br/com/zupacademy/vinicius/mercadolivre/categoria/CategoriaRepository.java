package br.com.zupacademy.vinicius.mercadolivre.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findCategoriaByIdAndParent(Long id);
}
