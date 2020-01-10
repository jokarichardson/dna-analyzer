package com.richardson.dna.application;

import com.richardson.dna.application.implementation.DnaAnalyzerApplicationImpl;
import com.richardson.dna.mocks.DnaMocks;
import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.service.DnaAnalyzerService;
import com.richardson.dna.validator.DnaValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DnaAnalyzerApplicationTest {
    
    @InjectMocks
    private DnaAnalyzerApplicationImpl dnaAnalyzerApplication;
    
    @Mock
    private DnaAnalyzerService dnaAnalyzerService;
    
    @Mock
    private DnaValidator dnaValidator;
    
    @Test
    public void givenADnaChainFindThatBelongsToASimianAndSaveIt() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioLinha();
        
        doNothing().when(this.dnaValidator).validaCadeiaDna(any());
        when(this.dnaAnalyzerService.isSimian(any())).thenReturn(Boolean.TRUE);
        when(this.dnaAnalyzerService.buscaCadeiasDnaPersistidas(any())).thenReturn(null);
        
        Boolean isSimian = this.dnaAnalyzerApplication.isSimian(cadeiasDna);
        
        assertTrue(isSimian);
        
        verify(this.dnaAnalyzerService, times(1)).isSimian(any());
        verify(this.dnaAnalyzerService, times(1)).buscaCadeiasDnaPersistidas(any());
        verify(this.dnaAnalyzerService, times(1)).salvarCadeiasDna(any());
    }
    
    @Test
    public void givenADnaChainFindThatBelongsToASimianAndDontSaveIt() {
        CadeiasDna cadeiasDna = DnaMocks.criaCadeiasDnaSimioLinha();
        List<CadeiasDna> cadeiasDnaList = Collections.singletonList(DnaMocks.criaCadeiasDnaSimioLinha());
        
        doNothing().when(this.dnaValidator).validaCadeiaDna(any());
        when(this.dnaAnalyzerService.isSimian(any())).thenReturn(Boolean.TRUE);
        when(this.dnaAnalyzerService.buscaCadeiasDnaPersistidas(any())).thenReturn(cadeiasDnaList);
        
        Boolean isSimian = this.dnaAnalyzerApplication.isSimian(cadeiasDna);
        
        assertTrue(isSimian);
        
        verify(this.dnaAnalyzerService, times(1)).isSimian(any());
        verify(this.dnaAnalyzerService, times(1)).buscaCadeiasDnaPersistidas(any());
        verify(this.dnaAnalyzerService, times(0)).salvarCadeiasDna(any());
    }
    
    
}
