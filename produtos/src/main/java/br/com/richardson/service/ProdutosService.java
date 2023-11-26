package br.com.richardson.service;

import br.com.richardson.model.dto.ProdutoDTO;
import br.com.richardson.model.entity.Produto;
import br.com.richardson.repository.ProdutosRepository;
import br.com.richardson.tools.ProdutosConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutosService {

    @Autowired
    private List<ProdutoDTO> produtoDTOList;
    @Autowired
    private ProdutosRepository produtosRepository;

    public ResponseEntity<?> getProdutos() {
        return ResponseEntity.ok(produtosRepository.findAll().stream().map(ProdutosConverter::produtoToProdutoDTO).collect(Collectors.toList()));
    }

    public ResponseEntity<?> getProdutoById(String id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não informado!");
        }
        
        Produto produto = produtosRepository.findById(UUID.fromString(id)).orElse(null);

        if (Objects.isNull(produto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        
        return ResponseEntity.ok(ProdutosConverter.produtoToProdutoDTO(produto));
    }

    public ResponseEntity<?> saveProduto(ProdutoDTO produtoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutosConverter.produtoToProdutoDTO(produtosRepository.save(ProdutosConverter.produtoDTOToProduto(produtoDTO))));
    }
    
    public ResponseEntity<?> deleteProduto(String id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não informado.");
            
        }
        if (getProdutoById(id).getStatusCode().is2xxSuccessful()) {
            produtosRepository.deleteById(UUID.fromString(id));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
    }
}
