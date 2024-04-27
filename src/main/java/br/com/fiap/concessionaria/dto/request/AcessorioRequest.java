package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AcessorioRequest(

        @Size(min = 3,max = 50, message = "Nome inválido")
        @NotNull(message = "Nome é obrigátorio")
        String nome,

        @Positive(message = "O valor não pode ser negativo")
        @NotNull(message = "Preço é obrigátorio")
        Double preco
) {
}
