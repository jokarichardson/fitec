package br.com.richardson.config;

import br.com.richardson.model.dto.ItemVendaDTO;
import br.com.richardson.model.dto.VendaDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Profile("test")
@Configuration
public class VendasBeanConfigTest {
    @Bean
    @Primary
    public List<VendaDTO> vendaDTOList() {
        return buildVendaDTOs();
    }

    private List<VendaDTO> buildVendaDTOs() {
        UUID idVenda = UUID.randomUUID();
        List<VendaDTO> vendaDTOS = new ArrayList<>();
        List<ItemVendaDTO> itemVendaDTOS = new ArrayList<>();

        itemVendaDTOS.add(ItemVendaDTO.builder()
                .id(UUID.randomUUID())
                .idVenda(idVenda)
                .idProduto(UUID.randomUUID())
                .valorUnitario(15.32)
                .quantidade(10)
                .valorTotal(153.2)
                .idGarantia(UUID.randomUUID())
                .build());

        vendaDTOS.add(VendaDTO.builder()
                .id(idVenda)
                .itensVenda(itemVendaDTOS)
                .valorTotal(153.2)
                .build());

        return vendaDTOS;
    }
}
