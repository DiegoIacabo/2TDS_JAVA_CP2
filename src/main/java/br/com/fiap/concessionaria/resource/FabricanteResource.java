package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.FabricanteRequest;
import br.com.fiap.concessionaria.dto.response.FabricanteResponse;
import br.com.fiap.concessionaria.entity.Fabricante;
import br.com.fiap.concessionaria.entity.Foto;
import br.com.fiap.concessionaria.service.FabricanteService;
import br.com.fiap.concessionaria.service.FotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteResource implements ResourceDTO<Fabricante, FabricanteRequest, FabricanteResponse> {

    @Autowired
    private FabricanteService service;

    @Autowired
    private FotoService fotoService;

    @GetMapping
    public ResponseEntity<Collection<FabricanteResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "nomeFantasia", required = false) String nomeFantasia
    ) {
        var fabricante = Fabricante.builder()
                .nome(nome)
                .nomeFantasia(nomeFantasia)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Fabricante> example = Example.of(fabricante, matcher);
        Collection<Fabricante> fabricantes = service.findAll(example);

        var response = fabricantes.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<FabricanteResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<FabricanteResponse> save(@RequestBody @Valid FabricanteRequest r) {
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

    @Transactional
    @PostMapping(value = "/{id}/fotos")
    public ResponseEntity<?> upload(@RequestPart MultipartFile file, @PathVariable Long id){
        var entity = service.findById(id);
        if(Objects.isNull(entity))
            return ResponseEntity.notFound().build();

        var extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        Foto foto = Foto.builder()
                .src(entity.getClass().getSimpleName().toLowerCase()+"_"+entity.getId()+"_"+ UUID.randomUUID().toString()+extension)
                .build();

        if(!fotoService.upload(file, foto)) return ResponseEntity.badRequest().build();

        entity.setLogo(foto);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
