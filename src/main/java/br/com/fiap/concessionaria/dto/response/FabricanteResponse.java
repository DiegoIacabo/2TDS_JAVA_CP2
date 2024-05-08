package br.com.fiap.concessionaria.dto.response;

import br.com.fiap.concessionaria.entity.Foto;
import lombok.Builder;

@Builder
public record FabricanteResponse(

        Long id,

        String nome,

        String nomeFantasia,

        Foto logo
) {
}
