package br.com.fiap.springpgdeposito.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_DEPOSITO_PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(
            name = "SQ_PRODUTO",
            sequenceName = "SQ_PRODUTO",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NOME_PRODUTO")
    private String nome;

    @Column(name = "DESCRICAO_PRODUTO")
    private String descricao;

    private BigDecimal valor;
}
