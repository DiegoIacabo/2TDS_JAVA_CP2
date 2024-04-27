package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.CaracteristicaRequest;
import br.com.fiap.concessionaria.dto.response.CaracteristicaResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Caracteristica;
import br.com.fiap.concessionaria.entity.Fabricante;
import br.com.fiap.concessionaria.entity.TipoVeiculo;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.service.CaracteristicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Year;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/caracteristicas")
public class CaracteristicaResource implements ResourceDTO<Caracteristica, CaracteristicaRequest, CaracteristicaResponse> {

    @Autowired
    private CaracteristicaService service;

    @GetMapping
    public ResponseEntity<Collection<CaracteristicaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "descricao", required = false) String descricao,
            @RequestParam(name = "veiculo.nome", required = false) String veiculoNome,
            @RequestParam(name = "veiculo.cor", required = false) String veiculoCor,
            @RequestParam(name = "veiculo.preco", required = false) Double veiculoPreco,
            @RequestParam(name = "veiculo.cilindradas", required = false) Short veiculoCilindradas,
            @RequestParam(name = "veiculo.modelo", required = false) String veiculoModelo,
            @RequestParam(name = "veiculo.palavraDeEfeito", required = false) String veiculoPalavraDeEfeito,
            @RequestParam(name = "veiculo.anoFabricacao", required = false) Year veiculoAnoFrabricacao,
            @RequestParam(name = "fabricante.nome", required = false) String fabricanteNome,
            @RequestParam(name = "fabricante.nomeFantasia", required = false) String fabricanteNomeFantasia,
            @RequestParam(name = "tipo.nome", required = false) String tipoNome
    ) {
        var tipoVeiculo = TipoVeiculo.builder()
                .nome(tipoNome)
                .build();

        var fabricante = Fabricante.builder()
                .nome(fabricanteNome)
                .nomeFantasia(fabricanteNomeFantasia)
                .build();

        var veiculo = Veiculo.builder()
                .nome(veiculoNome)
                .cor(veiculoCor)
                .preco(veiculoPreco)
                .cilindradas(veiculoCilindradas)
                .modelo(veiculoModelo)
                .palavraDeEfeito(veiculoPalavraDeEfeito)
                .anoDeFabricacao(veiculoAnoFrabricacao)
                .tipo(tipoVeiculo)
                .fabricante(fabricante)
                .build();

        var caracteristica = Caracteristica.builder()
                .nome(nome)
                .descricao(descricao)
                .veiculo(veiculo)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Caracteristica> example = Example.of(caracteristica, matcher);
        Collection<Caracteristica> caracteristicas = service.findAll(example);

        var response = caracteristicas.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<CaracteristicaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<CaracteristicaResponse> save(@RequestBody @Valid CaracteristicaRequest r) {
        var entity = service.toEntity(r);
        var saved = service.save(entity);
        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
