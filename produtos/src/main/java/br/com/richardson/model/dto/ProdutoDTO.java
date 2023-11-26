package br.com.richardson.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProdutoDTO {
    private UUID id;
    private String nome;
    private Double valor;
    private int estoque_maximo;
    private int estoque_minimo;
    private int saldo_estoque;
    private String fornecedor;
    private Boolean garantia;

}
