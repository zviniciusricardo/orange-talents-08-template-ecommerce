package br.com.zupacademy.vinicius.mercadolivre.produto;

import br.com.zupacademy.vinicius.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.vinicius.mercadolivre.produto.imagem_produto.Imagem;
import br.com.zupacademy.vinicius.mercadolivre.produto.imagem_produto.ImagemForm;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_produto.Opiniao;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_produto.OpiniaoForm;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;
import br.com.zupacademy.vinicius.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = form.toModel(categoriaRepository, usuarioRepository, usuario);
        produto = produtoRepository.save(produto);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @PostMapping
    @RequestMapping("/{id}/imagens")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto adicionaImagem(
            @PathVariable("id") Long id, @Valid ImagemForm form, @AuthenticationPrincipal Usuario usuario) {
        List<Imagem> imagens = form.toModel(id, produtoRepository, usuario);
        Produto produto = produtoRepository.findById(id).get();
        produto.adicionarImagens(imagens);
        this.produtoRepository.save(produto);
        return new ProdutoDto(produto);
    }

    @PostMapping
    @RequestMapping("/{id}/opinioes")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto adicionaOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoForm form,
                                      @AuthenticationPrincipal Usuario usuario) {
        Opiniao opiniao = form.toModel(id, produtoRepository, usuario);
        Produto produto = produtoRepository.findById(id).get();
        produto.adicionarOpiniao(opiniao);
        this.produtoRepository.save(produto);
        return new ProdutoDto(produto);
    }
}