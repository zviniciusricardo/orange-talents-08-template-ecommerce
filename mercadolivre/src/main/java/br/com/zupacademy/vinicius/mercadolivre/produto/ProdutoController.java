package br.com.zupacademy.vinicius.mercadolivre.produto;

import br.com.zupacademy.vinicius.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.vinicius.mercadolivre.produto.imagem_produto.Imagem;
import br.com.zupacademy.vinicius.mercadolivre.produto.imagem_produto.ImagemForm;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_usuario_produto.Opiniao;
import br.com.zupacademy.vinicius.mercadolivre.produto.opiniao_usuario_produto.OpiniaoForm;
import br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto.EmailSender;
import br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto.Pergunta;
import br.com.zupacademy.vinicius.mercadolivre.produto.pergunta_usuario_produto.PerguntaForm;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;
import br.com.zupacademy.vinicius.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = form.toModel(categoriaRepository, usuarioRepository, usuario);
        produto = produtoRepository.save(produto);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @PostMapping
    @RequestMapping("/{id}/imagens")
    public ResponseEntity<?> adicionaImagem(@PathVariable("id") Long id, @Valid ImagemForm form,
            @AuthenticationPrincipal Usuario usuario) {
        List<Imagem> imagens = form.toModel(id, produtoRepository, usuario);
        Produto produto = produtoRepository.findById(id).get();
        produto.setImagensProduto(imagens);
        this.produtoRepository.save(produto);
        return ResponseEntity.ok().body(new ProdutoDto(produto));
    }

    @PostMapping
    @RequestMapping("/{id}/opinioes")
    public ResponseEntity<?> adicionaOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoForm form,
                                      @AuthenticationPrincipal Usuario usuario) {
        Opiniao opiniao = form.toModel(id, produtoRepository, usuario);
        Produto produto = produtoRepository.findById(id).get();
        produto.setOpiniaoProduto(opiniao);
        this.produtoRepository.save(produto);
        return ResponseEntity.ok().body(new ProdutoDto(produto));
    }

    @PostMapping
    @RequestMapping("/{id}/perguntas")
    public ResponseEntity<?> adicionaPergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaForm form,
                                              @AuthenticationPrincipal Usuario usuario) {
        Pergunta pergunta = form.toModel(id, produtoRepository, usuario);
        Produto produto = produtoRepository.findById(id).get();
        produto.setPerguntaProduto(pergunta);
        this.produtoRepository.save(produto);
        EmailSender.sendMail(produto.getUsuario(), usuario);
        return ResponseEntity.ok().body(new ProdutoDto(produto));
    }

    @GetMapping
    @RequestMapping("/{id}/info")
    public ResponseEntity<?> detalhar(@PathVariable("id") Long id) {
        Produto produto = this.produtoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Não existe um produto com esse id."));
        return ResponseEntity.ok().body(new DetalhesProdutoDto(produto));
    }
}