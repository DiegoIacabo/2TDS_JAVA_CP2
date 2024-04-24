package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_VEICULO")
public class Veiculo {

    @Id
    @Column(name = "ID_VEICULO")
    @GeneratedValue(generator = "SQ_VEICULO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_VEICULO", sequenceName = "SQ_VEICULO", allocationSize = 1)
    private Long id;

    @Column(name = "NM_VEICULO")
    private String nome;

    private Year anoDeFabricacao;

    private String cor;

    @Column(name = "PR_VEICULO")
    private Double preco;

    private Short cilindradas;

    private String modelo;

    //15 digitos
    private String palavraDeEfeito;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "FABRICANTE",
            referencedColumnName = "ID_FABRICANTE",
            foreignKey = @ForeignKey(name = "FK_VEICULO_FABRICANTE"))
    private Fabricante fabricante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "TIPO",
            referencedColumnName = "ID_TIPO",
            foreignKey = @ForeignKey(name = "FK_VEICULO_TIPO"))
    private TipoVeiculo tipo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_VEICULO_ACESSORIO",
            joinColumns = {
                    @JoinColumn(
                            name = "VEICULO",
                            referencedColumnName = "ID_VEICULO",
                            foreignKey = @ForeignKey(name = "FK_VEICULO_ACESSORIO")
                    )
            }, inverseJoinColumns = {
                    @JoinColumn(
                             name = "ACESSORIO",
                            referencedColumnName = "ID_ACESSORIO",
                            foreignKey = @ForeignKey(name = "FK_ACESSORIO_VEICULO")
                    )
    }
    )
    private Set<Acessorio> acessorios = new LinkedHashSet<>();

}
