package br.com.richardson.service;

import br.com.richardson.model.dto.ProdutoDTO;
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
public class OrquestradorProdutosService {
    
    @Autowired
    ProdutosService produtosService;
    
    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<?> getProdutos() {
        try {
            return produtosService.getProdutos();
        } catch (Exception e) {
            log.info("Erro ao buscar produtos: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar produtos -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> getProdutoById(String id) {
        try {
            return produtosService.getProdutoById(id);
        } catch (Exception e) {
            log.info("Erro ao buscar o produto de id {}: ", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar o produto de id {}: " + id + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> addProduto(ProdutoDTO produtoDTO) throws JsonProcessingException {
        try {
            return produtosService.saveProduto(produtoDTO);
        } catch (Exception e) {
            log.info("Erro ao incluir o produto: {} -> ", mapper.writeValueAsString(produtoDTO), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao incluir o produto: {}" + mapper.writeValueAsString(produtoDTO) + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateProduto(ProdutoDTO produtoDTO) throws JsonProcessingException {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtosService.saveProduto(produtoDTO).getBody());
        } catch (Exception e) {
            log.info("Erro ao alterar o produto: {} -> ", mapper.writeValueAsString(produtoDTO), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao alterar o produto: {}" + mapper.writeValueAsString(produtoDTO) + " -> " + e.getMessage());
        }
    }

    public ResponseEntity<?> removeProduto(String id) {
        try {
            return produtosService.deleteProduto(id);
        } catch (Exception e) {
            log.info("Erro ao excluir o produto de id: {} -> ", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir o produto de id: {}" + id + " -> " + e.getMessage());
        }
    }
}
