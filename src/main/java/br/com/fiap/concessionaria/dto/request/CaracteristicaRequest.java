package br.com.fiap.concessionaria.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CaracteristicaRequest {


    @Size(min = 3,max = 30, message = "Nome inválido")
    @NotNull(message = "Nome é obrigátorio")
    //30 digitos
    private String nome;

    @Size(min = 3,max = 20, message = "Descrição está inválido")
    @NotNull(message = "Descrição é obrigátorio")
    //20 digitos
    private String descricao;

    @Positive
    @NotNull(message = "Veiculo é obrigátorio")
    private AbstractRequest veiculo;


}
