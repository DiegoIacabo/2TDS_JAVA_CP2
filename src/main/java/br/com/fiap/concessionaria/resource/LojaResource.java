package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.AbstractRequest;
import br.com.fiap.concessionaria.dto.request.LojaRequest;
import br.com.fiap.concessionaria.dto.request.VeiculoRequest;
import br.com.fiap.concessionaria.dto.response.CaracteristicaResponse;
import br.com.fiap.concessionaria.dto.response.LojaResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.*;
import br.com.fiap.concessionaria.service.LojaService;
import br.com.fiap.concessionaria.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Year;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/loja")
public class LojaResource implements ResourceDTO<Loja, LojaRequest, LojaResponse> {

    @Autowired
    private LojaService service;

    @Autowired
    VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<Collection<LojaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
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

        Set<Veiculo> collection = new LinkedHashSet<>();
        collection.add(veiculo);

        var loja = Loja.builder()
                .nome(nome)
                .veiculosComercializados(collection)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Loja> example = Example.of(loja, matcher);
        Collection<Loja> lojas = service.findAll(example);

        var response = lojas.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<LojaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<LojaResponse> save(@RequestBody @Valid LojaRequest r) {
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

    //@GetMapping(value = "/{id}/veiculos")
    //public ResponseEntity<Collection<VeiculoResponse>> findVeiculoByLoja(@PathVariable Long id) {
    //    var veiculo = veiculoService.findByLojaId(id);
    //    var response = veiculo.stream().map(veiculoService::toResponse).toList();
    //    return ResponseEntity.ok(response);
    //}

    @Transactional
    @PostMapping(value = "/{id}/veiculos")
    public ResponseEntity<VeiculoResponse> save(@PathVariable Long id, @RequestBody @Valid AbstractRequest veiculo) {

        var loja = service.findById(id);
        if (Objects.isNull(veiculo)) return ResponseEntity.badRequest().build();

        Veiculo veiculoEntity = null;
        if (Objects.nonNull(veiculo.id())){
            veiculoEntity = veiculoService.findById(veiculo.id());
        }
        loja.getVeiculosComercializados().add(veiculoEntity);

        var saved = veiculoService.save(veiculoEntity);
        var response = veiculoService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
