package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CaracteristicaRequest(

        @Size(min = 3, max = 30, message = "Nome inválido")
        @NotNull(message = "Nome é obrigátorio")
        //30 digitos
        String nome,

        @Size(min = 3, max = 20, message = "Descrição está inválida")
        @NotNull(message = "Descrição é obrigátoria")
        //20 digitos
        String descricao,

        @NotNull(message = "Veiculo é obrigátorio")
        AbstractRequest veiculo
) {
}
