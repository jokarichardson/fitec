package br.com.richardson.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="itens_vendas")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class ItemVenda implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Id
    @Column(name = "id_venda")
    @Type(type = "uuid-char")
    private UUID idVenda;
    
    @Column(name = "id_produto")
    @Type(type = "uuid-char")
    private UUID idProduto;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "valor_unitario")
    private Double valorUnitario;
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "id_garantia")
    @Type(type = "uuid-char")
    private UUID idGarantia;
}
