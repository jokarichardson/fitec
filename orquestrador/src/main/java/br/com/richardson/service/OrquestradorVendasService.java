package br.com.richardson.service;

import br.com.richardson.model.dto.VendaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class OrquestradorVendasService {
    
    @Autowired
    VendasService vendasService;

    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<?> getVendas() {
        try {
            return vendasService.getVendas();
        } catch (Exception e) {
            log.info("Erro ao buscar as vendas: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar as vendas -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> getVendaById(String id) {
        try {
            return vendasService.getVendaById(id);
        } catch (Exception e) {
            log.info("Erro ao buscar a venda de id {}: ", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar a venda de id {}: " + id + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> addVenda(VendaDTO vendaDTO) throws JsonProcessingException {
        try {
            return vendasService.saveVenda(vendaDTO);
        } catch (Exception e) {
            log.info("Erro ao incluir a venda: {} -> ", mapper.writeValueAsString(vendaDTO), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao incluir a venda: {}" + mapper.writeValueAsString(vendaDTO) + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> deleteVenda(String id) {
        try {
            return vendasService.deleteVenda(id);
        } catch (Exception e) {
            log.info("Erro ao excluir a venda de id: {} -> ", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir a venda de id: {}" + id + " -> " + e.getMessage());
        }
    }
}
