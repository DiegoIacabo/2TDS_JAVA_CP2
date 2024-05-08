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
@Table(name = "TB_FOTO")
public class Foto {

    @Id
    @Column(name = "ID_FOTO")
    @GeneratedValue(generator = "SQ_FOTO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_FOTO", sequenceName = "SQ_FOTO", allocationSize = 1)
    private Long id;

    @Column(name = "SRC_FOTO")
    private String src;

}
