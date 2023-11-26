package br.com.richardson.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GarantiaDTO {
    private String id;
    private String nome;
    private Double valor;
    private int prazo;
}
