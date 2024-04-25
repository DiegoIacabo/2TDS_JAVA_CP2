package br.com.fiap.concessionaria.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Caracteristica {

    private Long id;

    //30 digitos
    private String nome;

    //20 digitos
    private String descricao;

    private Veiculo veiculo;


}
