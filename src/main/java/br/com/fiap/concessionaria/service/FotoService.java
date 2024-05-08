package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.entity.Foto;
import br.com.fiap.concessionaria.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FotoService {

    private final String IMAGEM_FOLDER = System.getProperty("user.dir") + "/file_server/fotos";

    @Autowired
    private FotoRepository repo;

    public Foto save(Foto foto, MultipartFile file){
        Foto saved = null;
        if(upload(file, foto)) saved = repo.save(foto);
        return saved;
    }

    public boolean upload(MultipartFile file, Foto foto){
        if(file.isEmpty()) throw new RuntimeException("Empty file");

        Path destino = Paths.get(IMAGEM_FOLDER)
                .resolve(foto.getSrc())
                .normalize()
                .toAbsolutePath();

        try {
            Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
            return true;
        }catch (IOException ex){
            return false;
        }

    }

}
