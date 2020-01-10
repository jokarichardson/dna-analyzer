package com.richardson.dna.validator;

import com.richardson.dna.exception.DnaAnalyzerException;
import com.richardson.dna.mocks.DnaMocks;
import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.support.MessageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DnaValidatorTest {

    @InjectMocks
    private DnaValidator dnaValidator;
    
    @Mock
    private MessageUtils messageUtils;
    
    @Test
    public void givenADnaChainShouldConsiderItValid() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaHumano();
        
        this.dnaValidator.validaCadeiaDna(cadeiasDna.getDna());
    }
    
    @Test(expected = DnaAnalyzerException.class)
    public void givenADnaChainShouldConsiderItInvalidByInvalidNitrogenousBase() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaInvalida();
        
        this.dnaValidator.validaCadeiaDna(cadeiasDna.getDna());
    }
    
    @Test(expected = DnaAnalyzerException.class)
    public void givenADnaChainShouldConsiderItInvalidBecauseItsNotASquareMatrix() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaInvalidaNaoQuadrada();
        
        this.dnaValidator.validaCadeiaDna(cadeiasDna.getDna());
    }
    
    @Test(expected = DnaAnalyzerException.class)
    public void givenADnaChainShouldConsiderItInvalidBecauseItHasEmptyDnaChain() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaInvalidaComVazio();
        
        this.dnaValidator.validaCadeiaDna(cadeiasDna.getDna());
    }
}
