package br.com.richardson.config;

import br.com.richardson.model.dto.ProdutoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class ProdutoBeanConfig {
    
    @Bean
    public List<ProdutoDTO> produtoDTOList() {
        return buildProdutoDTOs();
    }

    private List<ProdutoDTO> buildProdutoDTOs() {
        List<ProdutoDTO> produtoDTOS = new ArrayList<>();

        produtoDTOS.add(ProdutoDTO.builder()
                .id(UUID.randomUUID())
                .nome("Produto 1")
                .valor(10.55)
                .estoque_maximo(100)
                .estoque_minimo(10)
                .saldo_estoque(57)
                .fornecedor("Fornecedor 1")
                .garantia(true)
                .build());

        produtoDTOS.add(ProdutoDTO.builder()
                .id(UUID.randomUUID())
                .nome("Produto 2")
                .valor(15.32)
                .estoque_maximo(450)
                .estoque_minimo(5)
                .saldo_estoque(364)
                .fornecedor("Fornecedor 2")
                .garantia(false)
                .build());

        return produtoDTOS;
    }
}
