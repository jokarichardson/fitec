package br.com.richardson.service;

import br.com.richardson.config.VendasBeanConfigTest;
import br.com.richardson.model.dto.ItemVendaDTO;
import br.com.richardson.model.dto.VendaDTO;
import br.com.richardson.model.entity.ItemVenda;
import br.com.richardson.model.entity.Venda;
import br.com.richardson.repository.VendasRepository;
import br.com.richardson.tools.VendasConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = VendasBeanConfigTest.class)
public class VendasServiceTest {
    VendasService vendasService;

    @Mock
    VendasRepository vendasRepository;
    
    @Autowired
    List<VendaDTO> vendaDTOList;
    
    @Before
    public void setUp() {
        vendaDTOList.add(buildVendaDTO());
        this.vendasService = new VendasService(vendaDTOList, vendasRepository);
    }
    
    @Test
    public void getVendas() {
        when(vendasRepository.findAll()).thenReturn(buildVendas());

        ResponseEntity<?> result = vendasService.getVendas();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getVendaById() {
        when(vendasRepository.findById(any())).thenReturn(Optional.of(buildVendas().get(0)));

        ResponseEntity<?> result = vendasService.getVendaById(vendaDTOList.get(0).getId().toString());

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
        assertTrue(result.getBody() instanceof VendaDTO);
    }

    @Test
    public void getVendaById_NotFound() {
        
        when(vendasRepository.findById(any())).thenReturn(Optional.empty());

        ResponseEntity<?> result = vendasService.getVendaById("xxxx-xxxx-xxxx-xxxx-xxx");

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void getVendaById_BadRequest() {
        ResponseEntity<?> result = vendasService.getVendaById(null);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void saveVenda() {
        VendaDTO vendaDTO = buildVendaDTO();
        vendaDTO.setId(UUID.randomUUID());
        
        Venda venda = new Venda();
        venda.setId(vendaDTO.getId());
        venda.setItensVenda(VendasConverter.itemVendaDTOToItemVenda(vendaDTO));
        venda.setValorTotal(vendaDTO.getValorTotal());

        when(vendasRepository.save(any())).thenReturn(venda);
        
        ResponseEntity<?> result = vendasService.saveVenda(vendaDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertTrue(result.hasBody());
        assertTrue(result.getBody() instanceof VendaDTO);
        VendaDTO vendaResult = (VendaDTO) result.getBody();
        assertEquals(vendaDTO.getId(), vendaResult.getId());
    }

    @Test
    public void deleteVenda() {
        when(vendasRepository.findById(any())).thenReturn(Optional.of(buildVendas().get(0)));
        doNothing().when(vendasRepository).delete(any());

        ResponseEntity<?> result = vendasService.deleteVenda(vendaDTOList.get(0).getId().toString());
        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

    @Test
    public void deleteVenda_BadRequest() {
        ResponseEntity<?> result = vendasService.deleteVenda(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteVenda_NotFound() {
        when(vendasRepository.findById(any())).thenReturn(Optional.empty());

        ResponseEntity<?> result = vendasService.deleteVenda(UUID.randomUUID().toString());

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    private List<Venda> buildVendas() {
        UUID idVenda = UUID.randomUUID();
        List<Venda> vendas = new ArrayList<>();
        List<ItemVenda> itensVenda = new ArrayList<>();
        itensVenda.add(new ItemVenda(UUID.randomUUID(), idVenda, UUID.randomUUID(), 10, 15.32, 153.2, UUID.randomUUID()));
        
        vendas.add(new Venda(UUID.randomUUID(),
               itensVenda,
                153.2));
        
        return vendas;
    }
    
    private VendaDTO buildVendaDTO() {
        UUID idVenda = UUID.fromString("2f428bbc-8ca6-11ee-b9d1-0242ac120002");

        List<ItemVendaDTO> itemVendaDTOS = new ArrayList<>();
        itemVendaDTOS.add(ItemVendaDTO.builder()
                .idVenda(idVenda)
                .idProduto(UUID.randomUUID())
                .valorUnitario(15.32)
                .quantidade(10)
                .valorTotal(153.2)
                .idGarantia(UUID.randomUUID())
                .build());

        return VendaDTO.builder()
                .id(idVenda)
                .itensVenda(itemVendaDTOS)
                .valorTotal(153.2)
                .build();
    }
}
