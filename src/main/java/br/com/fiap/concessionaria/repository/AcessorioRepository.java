package br.com.fiap.concessionaria.repository;

import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {

    //List<Acessorio> findByVeiculoId(Long id);
}
