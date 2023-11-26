package br.com.richardson.service;

import br.com.richardson.model.dto.GarantiaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@Slf4j
public class OrquestradorGarantiasService {
    
    @Autowired
    GarantiasService garantiasService;
    
    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<?> getGarantias() {
        try {
            return garantiasService.getGarantias();
        } catch (Exception e) {
            log.info("Erro ao buscar garantias: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar garantias -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> getGarantiaById(String id) {
        try {
            return garantiasService.getGarantiaById(id);
        } catch (Exception e) {
            log.info("Erro ao buscar a garantias de id {}: ", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar a garantia de id {}: " + id + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> addGarantia(GarantiaDTO garantiaDTO) throws JsonProcessingException {
        try {
            return garantiasService.saveGarantia(garantiaDTO);
        } catch (Exception e) {
            log.info("Erro ao incluir a garantia: {} -> ", mapper.writeValueAsString(garantiaDTO), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao incluir a garantia: {}" + mapper.writeValueAsString(garantiaDTO) + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateGarantia(GarantiaDTO garantiaDTO) throws JsonProcessingException {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(garantiasService.saveGarantia(garantiaDTO).getBody());
        } catch (Exception e) {
            log.info("Erro ao alterar a garantia: {} -> ", mapper.writeValueAsString(garantiaDTO), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao alterar a garantia: {}" + mapper.writeValueAsString(garantiaDTO) + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> deleteGarantia(String id) {
        try {
            return garantiasService.deleteGarantia(id);
        } catch (Exception e) {
            log.info("Erro ao excluir a garantia de id: {} -> ", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir a garantia de id: {}" + id + " -> " + e.getMessage());
        }
    }
}
