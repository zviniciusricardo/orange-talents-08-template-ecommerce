package br.com.zupacademy.vinicius.mercadolivre.produto.caracteristica_produto;

public class CaracteristicaDto {

    private Long id;
    private String nome;
    private String descricao;

    public CaracteristicaDto(Caracteristica caracteristica) {
        this.id = caracteristica.getId();
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
