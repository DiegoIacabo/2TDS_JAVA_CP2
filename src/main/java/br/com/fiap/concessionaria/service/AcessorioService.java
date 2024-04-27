package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.AcessorioRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.repository.AcessorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AcessorioService implements ServiceDTO<Acessorio, AcessorioRequest, AcessorioResponse> {

    @Autowired
    private AcessorioRepository repo;

    @Override
    public Collection<Acessorio> findAll(Example<Acessorio> example) {
        return repo.findAll(example);
    }

    @Override
    public Acessorio findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Acessorio save(Acessorio e) {
        return repo.save(e);
    }

    @Override
    public Acessorio toEntity(AcessorioRequest dto) {
        return Acessorio.builder()
                .preco(dto.preco())
                .nome(dto.nome())
                .build();
    }

    @Override
    public AcessorioResponse toResponse(Acessorio e) {
        return AcessorioResponse.builder()
                .id(e.getId())
                .preco(e.getPreco())
                .nome(e.getNome())
                .build();
    }

    //public List<Acessorio> findByVeiculoId(Long id) {
    //    return repo.findByVeiculoId(id);
    //}
}
