package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.Year;



public class VeiculoRequest {

    @Size(min = 3, max = 50, message = "Nome inválido")
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @Positive
    @NotNull(message = "Ano de fabricação é obrigatório")
    private Year anoDeFabricacao;

    @Size(max = 10, message = "Cor inválida")
    @NotNull(message = "Cor é obrigatória")
    private String cor;

    @Positive
    @NotNull(message = "Preço é obrigatório")
    private Double preco;

    @Positive
    @NotNull(message = "Cilindradas são obrigatórias")
    private Short cilindradas;

    @Size(min = 3, max = 20, message = "Nome inválido")
    @NotNull(message = "Modelo é obrigatório")
    private String modelo;


    // 15 dígitos
    @Size(min = 3, max = 15, message = "Palavra de efeito inválido")
    @NotNull(message = "Palavra de efeito é obrigatória")
    private String palavraDeEfeito;


    @NotNull(message = "Fabricante é obrigatório")
    private AbstractRequest fabricante;

    @NotNull(message = "Tipo é obrigatório")
    private AbstractRequest tipo;

}

