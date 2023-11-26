package br.com.richardson.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class VendaDTO implements Serializable {
    private UUID id;
    private List<ItemVendaDTO> itensVenda;
    private Double valorTotal;
}
