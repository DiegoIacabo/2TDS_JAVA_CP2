package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FabricanteRequest(

        @Size(min = 3, max = 50, message = "Nome inválido")
        @NotNull(message = "Nome é obrigátorio")
        String nome,

        @Size(min = 3, max = 50, message = "Nome fantasia inválido")
        @NotNull(message = "Nome fantasia é obrigátorio")
        String nomeFantasia
) {
}
