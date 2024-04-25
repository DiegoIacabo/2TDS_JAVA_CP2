package br.com.fiap.concessionaria.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

@Service
public class AcessorioRequest {

    @Size(min = 3,max = 50, message = "Nome inválido")
    @NotNull(message = "Nome é obrigátorio")
    private String nome;

    @Positive
    @NotNull(message = "Nome é obrigátorio")
    private Double preco;

}
