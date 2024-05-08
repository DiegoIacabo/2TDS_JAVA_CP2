package br.com.fiap.concessionaria.repository;

import br.com.fiap.concessionaria.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
}
