package com.richardson.dna.service;

import com.richardson.dna.mocks.DnaMocks;
import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.repository.DnaAnalyzerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DnaAnalyzerServiceTest {
    
    @InjectMocks
    private DnaAnalyzerService dnaAnalyzerService;
    
    @Mock
    private DnaAnalyzerRepository dnaAnalyzerRepository;
    
    @Test
    public void givenAMutantDnaUsingMatrixLineShouldReturnTrue() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioLinha();
        
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
        
        assertTrue(isSimian);
    }
    
    @Test
    public void givenAMutantDnaUsingMatrixColumnShouldReturnTrue() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioColuna();
    
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
    
        assertTrue(isSimian);
    }
    
    @Test
    public void givenAMutantDnaUsingMatrixPrincipalDiagonalShouldReturnTrue() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioDiagonalPrincipal();
    
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
    
        assertTrue(isSimian);
    }
    
    @Test
    public void givenAMutantDnaUsingMatrixUpperPrimaryDiagonalShouldReturnTrue() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioDiagonalAcimaDaPrincipal();
        
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
        
        assertTrue(isSimian);
    }
    
    @Test
    public void givenAMutantDnaUsingMatrixSecondaryDiagonalShouldReturnTrue() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioDiagonalSecundaria();
        
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
        
        assertTrue(isSimian);
    }
    
    @Test
    public void givenAMutantDnaUsingMatrixLowerSecondaryDiagonalShouldReturnTrue() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioDiagonalAbaixoDaSecundaria();
    
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
    
        assertTrue(isSimian);
    }
    
    @Test
    public void givenAHumanDnaShouldReturnFalse() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaHumano();
        
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
        
        assertFalse(isSimian);
    }
    
    @Test
    public void givenADnaChainShouldFindThatIsAlreadyPersisted() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaHumano();
        List<CadeiasDna> expectedCadeiasDnaList = Collections.singletonList(cadeiasDna);
        
        when(this.dnaAnalyzerRepository.findByCondition(any())).thenReturn(expectedCadeiasDnaList);
        
        List<CadeiasDna> actualCadeiasDnaList = this.dnaAnalyzerService.buscaCadeiasDnaPersistidas(cadeiasDna.getDna());
        
        assertFalse(CollectionUtils.isEmpty(actualCadeiasDnaList));
        assertEquals(expectedCadeiasDnaList, actualCadeiasDnaList);
        
        verify(this.dnaAnalyzerRepository, times(1)).findByCondition(any());
    }

    @Test
    public void givenADnaChainShouldPersistIt() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaHumano();
        
        this.dnaAnalyzerService.salvarCadeiasDna(cadeiasDna);
        
        verify(this.dnaAnalyzerRepository, times(1)).save(any());
    }
    
}
