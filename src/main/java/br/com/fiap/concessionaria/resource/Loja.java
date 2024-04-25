package br.com.fiap.concessionaria.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Loja {

    private Long id;

    private String nome;

    private Set<Veiculo> veiculosComercializados = new LinkedHashSet<>();

}
