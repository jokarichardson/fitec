package br.com.richardson.config;

import br.com.richardson.model.dto.GarantiaDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class GarantiasBeanConfig {
    @Bean
    public List<GarantiaDTO> garantiaDTOList() {
        return buildGarantiaDTOs();
    }

    private List<GarantiaDTO> buildGarantiaDTOs() {
        List<GarantiaDTO> GarantiaDTOS = new ArrayList<>();

        GarantiaDTOS.add(GarantiaDTO.builder()
                .id(UUID.randomUUID().toString())
                .nome("Garantia 1")
                .valor(10.55)
                .prazo(1)
                .build());

        GarantiaDTOS.add(GarantiaDTO.builder()
                .id(UUID.randomUUID().toString())
                .nome("Garantia 2")
                .valor(15.32)
                .prazo(2)
                .build());

        return GarantiaDTOS;
    }
}
