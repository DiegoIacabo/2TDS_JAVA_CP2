package br.com.fiap.concessionaria.service;


import br.com.fiap.concessionaria.dto.request.CaracteristicaRequest;
import br.com.fiap.concessionaria.dto.response.CaracteristicaResponse;
import br.com.fiap.concessionaria.entity.Caracteristica;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CaracteristicaService implements  ServiceDTO<Caracteristica, CaracteristicaRequest, CaracteristicaResponse>{

    @Autowired
    private CaracteristicaRepository repo;
    @Override
    public Collection<Caracteristica> findAll(Example<Caracteristica> example) {
        return null;
    }

    @Override
    public Caracteristica findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Caracteristica save(Caracteristica e) {
        return repo.save(e);
    }

    @Override
    public Caracteristica toEntity(CaracteristicaRequest dto) {
        Veiculo veiculo = veiculoService.findById(dto.veiculo().id())
        return Caracteristica.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao)
                .veiculo(veiculo)
                .build();
    }

    @Override
    public CaracteristicaResponse toResponse(Caracteristica e) {

        return CaracteristicaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .descricao(e.getDescricao())
                VeiculoService.ToResponse(e.getVeiculo())
                .build();
    }
}
