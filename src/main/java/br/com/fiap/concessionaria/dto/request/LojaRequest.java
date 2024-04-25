package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LojaRequest {

    @Size(min = 3,max = 50, message = "Nome inválido")
    @NotNull(message = "Nome é obrigátorio")
    private String nome;
}
