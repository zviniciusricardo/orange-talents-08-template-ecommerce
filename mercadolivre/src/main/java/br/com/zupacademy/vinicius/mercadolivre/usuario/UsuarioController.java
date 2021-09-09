package br.com.zupacademy.vinicius.mercadolivre.usuario;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criaUsuario(@RequestBody @Valid UsuarioForm form) {
        Usuario usuario = form.toModel();
        manager.persist(usuario);
        return ResponseEntity.ok().body(new UsuarioDto(usuario));
    }
}
