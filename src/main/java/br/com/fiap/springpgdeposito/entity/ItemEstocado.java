package br.com.fiap.springpgdeposito.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_DEPOSITO_ITEM_ESTOCADO")
public class ItemEstocado {
    @Id
    @Column(name = "ID_ITEM_ESTOCADO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITEM_ESTOCADO")
    @SequenceGenerator(name = "SQ_ITEM_ESTOCADO", sequenceName = "SQ_ITEM_ESTOCADO", allocationSize = 1)
    private Long id;
    @Column(name = "NUMERO_DE_SERIE_ITEM_ESTOCADO")
    private String numeroDeSerie;
    private LocalDateTime saida;
    private LocalDateTime entrada;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_ITEM-ESTOCADO"))
    private Produto produto;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "DEPOSITO", referencedColumnName = "ID_DEPOSITO", foreignKey = @ForeignKey(name = "FK_DEPOSITO_ITEM-ESTOCADO"))
    private Deposito deposito;

}