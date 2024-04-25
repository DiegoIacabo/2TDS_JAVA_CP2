package br.com.fiap.concessionaria.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

@Service
public class FabricanteRequest {


    @Size(min = 3,max = 50, message = "Nome inválido")
    @NotNull(message = "Nome é obrigátorio")
    private String nome;

    @Size(min = 3,max = 50, message = "Nome fantasia inválido")
    @NotNull(message = "Nome fantasia é obrigátorio")
    private String nomeFantasia;



}
