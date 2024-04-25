package br.com.fiap.concessionaria.dto.response;


import lombok.Builder;


import java.util.LinkedHashSet;
import java.util.Set;


@Builder
public class LojaResponse {

    private Long id;

    private String nome;

    private Set<VeiculoResponse> veiculosComercializados = new LinkedHashSet<>();

}
