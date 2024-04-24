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
@Table(name = "TB_TIPO_VEICULO", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NM_TIPO", columnNames = "NM_TIPO"
        )
})
public class TipoVeiculo {

    @Id
    @Column(name = "ID_TIPO")
    @GeneratedValue(generator = "SQ_TIPO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_TIPO", sequenceName = "SQ_TIPO", allocationSize = 1)
    private Long id;

    @Column(name = "NM_TIPO")
    private String nome;
}
