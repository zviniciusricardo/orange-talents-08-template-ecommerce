package br.com.zupacademy.vinicius.mercadolivre.categoria;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    CategoriaRepository categoriaRepository;

    public ResponseEntity<?> criaCategoria(@RequestBody @Valid CategoriaForm form) {
        Categoria categoria = form.toModel(categoriaRepository);
        manager.persist(categoria);
        return ResponseEntity.ok().body(new CategoriaDto(categoria));
    }
}
