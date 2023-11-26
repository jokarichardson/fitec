package br.com.richardson.service;

import br.com.richardson.model.dto.ProdutoDTO;
import br.com.richardson.model.entity.Produto;
import br.com.richardson.repository.ProdutosRepository;
import br.com.richardson.tools.ProdutosConverter;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProdutosServiceTest {
    @InjectMocks
    ProdutosService produtosService;

    @Mock
    ProdutosRepository produtosRepository;

    @Test
    public void getProdutos() {
        when(produtosRepository.findAll()).thenReturn(buildProdutosList());

        ResponseEntity<?> result = produtosService.getProdutos();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getProdutoById() {
        when(produtosRepository.findById(any())).thenReturn(Optional.of(buildProdutosList().get(0)));
        
        ResponseEntity<?> result = produtosService.getProdutoById("a12069ce-93bd-4227-9231-bee994b49774");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
        assertTrue(result.getBody() instanceof ProdutoDTO);
    }

    @Test
    public void getProdutoById_NotFound() {
        when(produtosRepository.findById(any())).thenReturn(Optional.empty());

        ResponseEntity<?> result = produtosService.getProdutoById("38b76b80-8be9-11ee-b9d1-0242ac120002");

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void getProdutoById_BadRequest() {
        ResponseEntity<?> result = produtosService.getProdutoById(null);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void saveProduto() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder()
                .id(UUID.fromString("2181450c-8bea-11ee-b9d1-0242ac120002"))
                .nome("Produto 3")
                .valor(130.99)
                .estoque_maximo(2500)
                .estoque_minimo(100)
                .saldo_estoque(557)
                .fornecedor("Fornecedor 3")
                .garantia(true)
                .build();

        when(produtosRepository.save(any())).thenReturn(ProdutosConverter.produtoDTOToProduto(produtoDTO));

        ResponseEntity<?> result = produtosService.saveProduto(produtoDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertTrue(result.hasBody());
        assertTrue(result.getBody() instanceof ProdutoDTO);
        ProdutoDTO produtoResult = (ProdutoDTO) result.getBody();
        assertTrue("2181450c-8bea-11ee-b9d1-0242ac120002".equalsIgnoreCase(produtoResult.getId().toString()));
    }

    @Test
    public void deleteProduto() {
        when(produtosRepository.findById(any())).thenReturn(Optional.of(buildProdutosList().get(0)));
        doNothing().when(produtosRepository).delete(any());
        
        ResponseEntity<?> result = produtosService.deleteProduto(UUID.randomUUID().toString());
        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

    @Test
    public void deleteProduto_BadRequest() {
        ResponseEntity<?> result = produtosService.deleteProduto(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    
    @Test
    public void deleteProduto_NotFound() {
        when(produtosRepository.findById(any())).thenReturn(Optional.empty());

        ResponseEntity<?> result = produtosService.deleteProduto(UUID.randomUUID().toString());

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    private List<Produto> buildProdutosList() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(UUID.randomUUID(),
                "Produto 1",
                10.55,
                100,
                10,
                57,
                "Fornecedor 1",
                true));

        produtos.add(new Produto(UUID.randomUUID(),
                "Produto 2",
                15.32,
                450,
                5,
                364,
                "Fornecedor 2",
                false));

        return produtos;
    }
}
