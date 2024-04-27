package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Year;

public record VeiculoRequest(

        @Size(min = 3, max = 50, message = "Nome inválido")
        @NotNull(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Ano de fabricação é obrigatório")
        Year anoDeFabricacao,

        @NotNull(message = "Cor é obrigatória")
        String cor,

        @Positive
        @NotNull(message = "Preço é obrigatório")
        Double preco,

        @Positive
        @NotNull(message = "Cilindradas são obrigatórias")
        Short cilindradas,

        @NotNull(message = "Modelo é obrigatório")
        String modelo,

        // 15 dígitos
        @Size(min = 3, max = 15, message = "Palavra de efeito inválida")
        @NotNull(message = "Palavra de efeito é obrigatória")
        String palavraDeEfeito,


        @NotNull(message = "Fabricante é obrigatório")
        AbstractRequest fabricante,

        @NotNull(message = "Tipo é obrigatório")
        AbstractRequest tipo
) {
}
