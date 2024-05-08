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
@Table(name = "TB_FABRICANTE")
public class Fabricante {

    @Id
    @Column(name = "ID_FABRICANTE")
    @GeneratedValue(generator = "SQ_FABRICANTE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_FABRICANTE", sequenceName = "SQ_FABRICANTE", allocationSize = 1)
    private Long id;

    @Column(name = "NM_FABRICANTE")
    private String nome;

    private String nomeFantasia;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "FOTO",
            referencedColumnName = "ID_FOTO",
            foreignKey = @ForeignKey(name = "FK_FABRICANTE_FOTO")
    )
    private Foto logo;

}
