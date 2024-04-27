package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.VeiculoRequest;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class VeiculoService implements ServiceDTO<Veiculo, VeiculoRequest, VeiculoResponse> {

    @Autowired
    private VeiculoRepository repo;

    @Autowired
    private AcessorioService acessorioService;

    @Autowired
    private TipoVeiculoService tipoVeiculoService;

    @Autowired
    private FabricanteService fabricanteService;

    @Override
    public Collection<Veiculo> findAll(Example<Veiculo> example) {
        return repo.findAll(example);
    }

    @Override
    public Veiculo findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Veiculo save(Veiculo e) {
        return repo.save(e);
    }

    @Override
    public Veiculo toEntity(VeiculoRequest dto) {
        var fabricante = fabricanteService.findById(dto.fabricante().id());
        var tipo = tipoVeiculoService.findById(dto.tipo().id());

        return Veiculo.builder()
                .nome(dto.nome())
                .cor(dto.cor())
                .preco(dto.preco())
                .cilindradas(dto.cilindradas())
                .anoDeFabricacao(dto.anoDeFabricacao())
                .modelo(dto.modelo())
                .palavraDeEfeito(dto.palavraDeEfeito())
                .fabricante(fabricante)
                .tipo(tipo)
                .build();
    }

    @Override
    public VeiculoResponse toResponse(Veiculo e) {
        var fabricante = fabricanteService.toResponse(e.getFabricante());
        var tipo = tipoVeiculoService.toResponse(e.getTipo());

        return VeiculoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .anoDeFabricacao(e.getAnoDeFabricacao())
                .cor(e.getCor())
                .preco(e.getPreco())
                .cilindradas(e.getCilindradas())
                .modelo(e.getModelo())
                .palavraDeEfeito(e.getPalavraDeEfeito())
                .fabricante(fabricante)
                .tipo(tipo)
                .build();
    }

    //public List<Veiculo> findByLojaId(Long id) {
    //    return repo.findByLojaId(id);
    //}

}
