package br.com.richardson.tools;

import br.com.richardson.model.dto.ItemVendaDTO;
import br.com.richardson.model.dto.VendaDTO;
import br.com.richardson.model.entity.ItemVenda;
import br.com.richardson.model.entity.Venda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class VendasConverterTest {

    @Test
    public void vendaToVendaDTO_NullVenda() {
        VendaDTO result = VendasConverter.vendaToVendaDTO(null);
        
        assertNull(result);
    }
    
    @Test
    public void itemVendaToItemVendaDTO() {
        List<ItemVendaDTO> result = VendasConverter.itemVendaToItemVendaDTO(Collections.singletonList(new ItemVenda(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 10, 15.32, 153.2, UUID.randomUUID())));
        
        assertNotNull(result);
    }
    
    @Test
    public void itemVendaToItemVendaDTO_NullItemVendaList() {
        List<ItemVendaDTO> result = VendasConverter.itemVendaToItemVendaDTO(null);
        
        assertNull(result);
    }

    @Test
    public void itemVendaDTOToItemVenda_NullItemVendaDTOList() {
        List<ItemVenda> result = VendasConverter.itemVendaDTOToItemVenda(VendaDTO.builder().itensVenda(null).id(UUID.randomUUID()).valorTotal(0.00).build());

        assertNull(result);
    }
    
    @Test
    public void vendaToVendaDTO() {
        VendaDTO result = VendasConverter.vendaToVendaDTO(new Venda(UUID.randomUUID(), Collections.emptyList(), 100.00));
        assertNotNull(result);
    }
}
