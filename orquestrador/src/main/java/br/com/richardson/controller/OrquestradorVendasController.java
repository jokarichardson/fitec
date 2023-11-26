package br.com.richardson.controller;

import br.com.richardson.config.SwaggerConfig;
import br.com.richardson.model.dto.GarantiaDTO;
import br.com.richardson.model.dto.VendaDTO;
import br.com.richardson.service.OrquestradorVendasService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fitec/vendas")
public class OrquestradorVendasController {
    
    @Autowired
    private OrquestradorVendasService vendasService;

    @GetMapping(produces="application/json", name="getVendas")
    @ApiOperation(value = "Buscar Vendas")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = Iterable.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class) })
    public ResponseEntity<?> getVendas() {
        return vendasService.getVendas();
    }

    @GetMapping(path="/{id}", produces="application/json", name="getVendaById")
    public ResponseEntity<?> getVenda(@PathVariable(name="id") String id) {
        return vendasService.getVendaById(id);
    }

    @PostMapping(produces="application/json", name="addVenda")
    public ResponseEntity<?> addVenda(@RequestBody VendaDTO vendaDTO) {
        try {
            return vendasService.addVenda(vendaDTO);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao incluir a venda -> " + e);
        }
    }

    @DeleteMapping(path="/{id}", produces = "application/json", name = "deleteVenda")
    public ResponseEntity<?> deleteVenda(@PathVariable(name = "id") String id) {
        return vendasService.deleteVenda(id);
    }
}
