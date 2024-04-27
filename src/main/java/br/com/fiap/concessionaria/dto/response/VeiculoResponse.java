package br.com.fiap.concessionaria.dto.response;

import lombok.Builder;

import java.time.Year;

@Builder
public record VeiculoResponse(

        Long id,

        String nome,

        Year anoDeFabricacao,

        String cor,

        Double preco,

        Short cilindradas,

        String modelo,

        String palavraDeEfeito,

        FabricanteResponse fabricante,

        TipoVeiculoResponse tipo
) {
}
