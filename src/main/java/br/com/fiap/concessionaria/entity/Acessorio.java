package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_ACESSORIO")
public class Acessorio {

    @Id
    @Column(name = "ID_ACESSORIO")
    @GeneratedValue(generator = "SQ_ACESSORIO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_ACESSORIO", sequenceName = "SQ_ACESSORIO", allocationSize = 1)
    private Long id;

    @Column(name = "NM_ACESSORIO")
    private String nome;

    @Column(name = "PR_ACESSORIO")
    private Double preco;

}
