package br.com.richardson.service;

import br.com.richardson.model.dto.GarantiaDTO;
import br.com.richardson.model.entity.Garantia;
import br.com.richardson.repository.GarantiasRepository;
import br.com.richardson.tools.GarantiasConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GarantiasServiceTest {
    @InjectMocks
    GarantiasService garantiasService;

    @Mock
    GarantiasRepository garantiasRepository;

    @Test
    public void getGarantias() {
        when(garantiasRepository.findAll()).thenReturn(buildGarantias());

        ResponseEntity<?> result = garantiasService.getGarantias();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getGarantiaById() {
        when(garantiasRepository.findById(any())).thenReturn(Optional.of(buildGarantias().get(0)));

        ResponseEntity<?> result = garantiasService.getGarantiaById("a12069ce-93bd-4227-9231-bee994b49774");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
        assertTrue(result.getBody() instanceof GarantiaDTO);
    }

    @Test
    public void getGarantiaById_NotFound() {
        when(garantiasRepository.findById(any())).thenReturn(Optional.empty());

        ResponseEntity<?> result = garantiasService.getGarantiaById("38b76b80-8be9-11ee-b9d1-0242ac120002");

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void getGarantiaById_BadRequest() {
        ResponseEntity<?> result = garantiasService.getGarantiaById(null);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void saveGarantia() {
        GarantiaDTO garantiaDTO = GarantiaDTO.builder()
                .id("2181450c-8bea-11ee-b9d1-0242ac120002")
                .nome("Garantia 3")
                .valor(15.00)
                .prazo(3)
                .build();

        when(garantiasRepository.save(any())).thenReturn(GarantiasConverter.garantiaDTOToGarantia(garantiaDTO));

        ResponseEntity<?> result = garantiasService.saveGarantia(garantiaDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertTrue(result.hasBody());
        assertTrue(result.getBody() instanceof GarantiaDTO);
        GarantiaDTO produtoResult = (GarantiaDTO) result.getBody();
        assertTrue("2181450c-8bea-11ee-b9d1-0242ac120002".equalsIgnoreCase(produtoResult.getId().toString()));
    }

    @Test
    public void deleteGarantia() {
        when(garantiasRepository.findById(any())).thenReturn(Optional.of(buildGarantias().get(0)));
        doNothing().when(garantiasRepository).delete(any());

        ResponseEntity<?> result = garantiasService.deleteGarantia(UUID.randomUUID().toString());
        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

    @Test
    public void deleteGarantia_BadRequest() {
        ResponseEntity<?> result = garantiasService.deleteGarantia(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void deleteGarantia_NotFound() {
        when(garantiasRepository.findById(any())).thenReturn(Optional.empty());

        ResponseEntity<?> result = garantiasService.deleteGarantia(UUID.randomUUID().toString());

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    private List<Garantia> buildGarantias() {
        List<Garantia> garantias = new ArrayList<>();
        garantias.add(new Garantia(UUID.randomUUID(),
                "Garantia 1",
                5.00,
                1));

        garantias.add(new Garantia(UUID.randomUUID(),
                "Garantia 2",
                10.00,
                2));

        return garantias;
    }
}
