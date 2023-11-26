package br.com.richardson.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="garantias")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Garantia {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Double valor;
    @Column(nullable = false)
    private Integer prazo;
}
