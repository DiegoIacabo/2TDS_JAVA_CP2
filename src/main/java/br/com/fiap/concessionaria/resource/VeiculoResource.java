package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.AbstractRequest;
import br.com.fiap.concessionaria.dto.request.VeiculoRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.dto.response.TipoVeiculoResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.entity.Fabricante;
import br.com.fiap.concessionaria.entity.TipoVeiculo;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.service.AcessorioService;
import br.com.fiap.concessionaria.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Year;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoResource implements ResourceDTO<Veiculo, VeiculoRequest, VeiculoResponse> {

    @Autowired
    private VeiculoService service;

    @Autowired
    private AcessorioService acessorioService;

    @GetMapping
    public ResponseEntity<Collection<VeiculoResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cor", required = false) String cor,
            @RequestParam(name = "preco", required = false) Double preco,
            @RequestParam(name = "cilindradas", required = false) Short cilindradas,
            @RequestParam(name = "modelo", required = false) String modelo,
            @RequestParam(name = "palavraDeEfeito", required = false) String palavraDeEfeito,
            @RequestParam(name = "anoFabricacao", required = false) Year anoFrabricacao,
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
                .nome(nome)
                .cor(cor)
                .preco(preco)
                .cilindradas(cilindradas)
                .modelo(modelo)
                .palavraDeEfeito(palavraDeEfeito)
                .anoDeFabricacao(anoFrabricacao)
                .tipo(tipoVeiculo)
                .fabricante(fabricante)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Veiculo> example = Example.of(veiculo, matcher);
        Collection<Veiculo> veiculos = service.findAll(example);

        var response = veiculos.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<VeiculoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<VeiculoResponse> save(@RequestBody @Valid VeiculoRequest r) {
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

    //@GetMapping(value = "/{id}/acessorios")
    //public ResponseEntity<Collection<AcessorioResponse>> findVeiculoByLoja(@PathVariable Long id) {
    //    var veiculo = acessorioService.findByVeiculoId(id);
    //    var response = veiculo.stream().map(acessorioService::toResponse).toList();
    //    return ResponseEntity.ok(response);
    //}

    @Transactional
    @PostMapping(value = "/{id}/acessorios")
    public ResponseEntity<AcessorioResponse> save(@PathVariable Long id, @RequestBody @Valid AbstractRequest acessorio) {

        var veiculo = service.findById(id);
        if (Objects.isNull(acessorio)) return ResponseEntity.badRequest().build();

        Acessorio acessorioEntity = null;
        if (Objects.nonNull(acessorio.id())){
            acessorioEntity = acessorioService.findById(acessorio.id());
        }
        veiculo.getAcessorios().add(acessorioEntity);

        var saved = acessorioService.save(acessorioEntity);
        var response = acessorioService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
