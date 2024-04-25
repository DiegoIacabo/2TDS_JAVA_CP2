package br.com.fiap.concessionaria.dto.response;


import lombok.Builder;
import java.time.Year;



@Builder



public class VeiculoResponse {

    private Long id;

    private String nome;

    private Year anoDeFabricacao;

    private String cor;

    private Double preco;

    private Short cilindradas;

    private String modelo;

    //15 digitos
    private String palavraDeEfeito;

    private FabricanteResponse fabricante;

    private TipoVeiculoResponse tipo;

}
