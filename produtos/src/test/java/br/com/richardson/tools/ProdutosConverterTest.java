package br.com.richardson.tools;

import br.com.richardson.model.dto.ProdutoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class ProdutosConverterTest {
    
    @Test
    public void produtoToProdutoDTO_NullProduto() {
        ProdutoDTO result = ProdutosConverter.produtoToProdutoDTO(null);
        
        assertNull(result);
    }
}
