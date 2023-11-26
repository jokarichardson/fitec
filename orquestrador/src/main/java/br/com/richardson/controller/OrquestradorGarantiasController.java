package br.com.richardson.controller;

import br.com.richardson.config.SwaggerConfig;
import br.com.richardson.model.dto.GarantiaDTO;
import br.com.richardson.service.OrquestradorGarantiasService;
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
@RequestMapping("/fitec/garantias")
public class OrquestradorGarantiasController {
    
    @Autowired
    private OrquestradorGarantiasService garantiasService;

    @GetMapping(produces="application/json", name="getGarantias")
    @ApiOperation(value = "Buscar Garantias")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = Iterable.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class) })
    public ResponseEntity<?> getGarantias() {
        return garantiasService.getGarantias();
    }

    @GetMapping(path="/{id}", produces="application/json", name="getGarantiaById")
    public ResponseEntity<?> getGarantia(@PathVariable(name="id") String id) {
        return garantiasService.getGarantiaById(id);
    }

    @PostMapping(produces="application/json", name="addGarantia")
    public ResponseEntity<?> addGarantia(@RequestBody GarantiaDTO garantiaDTO) {
        try {
            return garantiasService.addGarantia(garantiaDTO);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao incluir garantia -> " + e);
        }
    }

    @PutMapping(produces="application/json", name="updateGarantia")
    public ResponseEntity<?> updateGarantia(@RequestBody GarantiaDTO garantiaDTO) {
        try {
            return garantiasService.updateGarantia(garantiaDTO);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar garantia -> " + e);
        }
    }

    @DeleteMapping(path="/{id}", produces = "application/json", name = "deleteGarantia")
    public ResponseEntity<?> deleteGarantia(@PathVariable(name = "id") String id) {
        return garantiasService.deleteGarantia(id);
    }
}
