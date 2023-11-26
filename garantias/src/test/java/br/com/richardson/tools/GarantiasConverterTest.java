package br.com.richardson.tools;

import br.com.richardson.model.dto.GarantiaDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class GarantiasConverterTest {
    
    @Test
    public void garantiaToGarantiaDTO_NullGarantia() {
        GarantiaDTO result = GarantiasConverter.garantiaToGarantiaDTO(null);
        
        assertNull(result);
    }
}
