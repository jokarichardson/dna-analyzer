package com.richardson.dna.support;

import com.richardson.dna.model.response.ResponseMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class ResponseUtilsTest {

    @InjectMocks
    private ResponseUtils responseUtils;
    
    @Test
    public void shouldCreateAResponseMessage() {
        ResponseMessage responseMessage = ResponseUtils.criaResponseMessage(200, "Erro", "Mensagem", "classeExcecao", "/caminho");
        
        assertNotNull(responseMessage);
        
    }

}
