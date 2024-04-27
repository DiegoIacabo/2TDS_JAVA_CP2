package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_CARACTERISTICA", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NM_CARACTERIStICA_VEICULO",
                columnNames = {"NM_CARACTERISTICA", "VEICULO"}
        )
})
public class Caracteristica {

    @Id
    @Column(name = "ID_CARACTERISTICA")
    @GeneratedValue(generator = "SQ_CARACTERISTICA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_CARACTERISTICA", sequenceName = "SQ_CARACTERISTICA", allocationSize = 1)
    private Long id;

    //30 digitos
    @Column(name = "NM_CARACTERISTICA")
    private String nome;

    //20 digitos
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "VEICULO",
            referencedColumnName = "ID_VEICULO",
            foreignKey = @ForeignKey(name = "FK_CARACTERISTICA_VEICULO")
    )
    private Veiculo veiculo;


}
