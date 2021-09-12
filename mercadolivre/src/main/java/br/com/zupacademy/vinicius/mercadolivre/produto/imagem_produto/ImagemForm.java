package br.com.zupacademy.vinicius.mercadolivre.produto.imagem_produto;

import br.com.zupacademy.vinicius.mercadolivre.produto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImagemForm {


    @NotNull @Size(min = 1)
    private List<MultipartFile> imagens;

    public ImagemForm(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<Imagem> toModel(Long produtoId, ProdutoRepository produtoRepository, Usuario usuario) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);
        if (produto.isEmpty()) throw new IllegalArgumentException("Não existe um produto com esse id");

        if (!usuario.getId().equals(produto.get().getUsuario().getId()))
            throw new IllegalArgumentException("Você não pode adicionar uma imagem em um produto que não é seu.");

        List<Imagem> imagens = new ArrayList<>();
        this.imagens.forEach(imagem -> {
            imagens.add(new Imagem(imagem.getOriginalFilename()));
        });
        return imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
