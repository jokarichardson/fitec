package br.com.richardson.tools;

import br.com.richardson.model.dto.GarantiaDTO;
import br.com.richardson.model.entity.Garantia;

import java.util.Objects;
import java.util.UUID;

public class GarantiasConverter {
    public static Garantia garantiaDTOToGarantia(GarantiaDTO garantiaDTO) {
        return new Garantia(UUID.fromString(garantiaDTO.getId()),
                garantiaDTO.getNome(),
                garantiaDTO.getValor(),
                garantiaDTO.getPrazo());
    }

    public static GarantiaDTO garantiaToGarantiaDTO(Garantia garantia) {
        if (Objects.isNull(garantia)) {
            return null;
        }
        return GarantiaDTO.builder()
                .id(garantia.getId().toString())
                .nome(garantia.getNome())
                .valor(garantia.getValor())
                .prazo(garantia.getPrazo())
                .build();
    }
}
