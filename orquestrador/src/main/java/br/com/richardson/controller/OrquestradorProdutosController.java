package br.com.richardson.controller;

import br.com.richardson.config.SwaggerConfig;
import br.com.richardson.model.dto.ProdutoDTO;
import br.com.richardson.service.OrquestradorProdutosService;
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
@RequestMapping("/fitec/produtos")
public class OrquestradorProdutosController {
    
    @Autowired
    OrquestradorProdutosService produtosService;

    @GetMapping(produces="application/json", name="getProdutos")
    @ApiOperation(value = "Buscar Produtos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = Iterable.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class) })
    public ResponseEntity<?> getProdutos() {
        return produtosService.getProdutos();
    }

    @GetMapping(path="/{id}", produces="application/json", name="getProdutoById")
    public ResponseEntity<?> getProdutos(@PathVariable(name="id") String id) {
        return produtosService.getProdutoById(id);
    }

    @PostMapping(produces="application/json", name="addProduto")
    public ResponseEntity<?> addProduto(@RequestBody ProdutoDTO produtoDTO) {
        try {
            return produtosService.addProduto(produtoDTO);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao incluir produto -> " + e);
        }
    }

    @PutMapping(produces="application/json", name="updateProduto")
    public ResponseEntity<?> updateProduto(@RequestBody ProdutoDTO produtoDTO) {
        try {
            return produtosService.updateProduto(produtoDTO);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar produto -> " + e);
        }
    }

    @DeleteMapping(path="/{id}", produces = "application/json", name = "deleteProduto")
    public ResponseEntity<?> deleteProduto(@PathVariable(name = "id") String id) {
        return produtosService.removeProduto(id);
    }
}
