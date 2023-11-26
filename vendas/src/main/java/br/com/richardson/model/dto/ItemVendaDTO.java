package br.com.richardson.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
public class ItemVendaDTO implements Serializable {
    private UUID id;
    private UUID idVenda;
    private UUID idProduto;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal;
    private UUID idGarantia;
}
