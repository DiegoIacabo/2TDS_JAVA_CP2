package br.com.fiap.concessionaria.dto.response;


import lombok.Builder;
@Builder
public class CaracteristicaResponse {

    private Long id;

    //30 digitos
    private String nome;

    //20 digitos
    private String descricao;

    private VeiculoResponse veiculo;


}
