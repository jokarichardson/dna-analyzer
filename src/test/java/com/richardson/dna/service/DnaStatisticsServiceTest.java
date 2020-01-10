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

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DnaStatisticsServiceTest {
    
    @InjectMocks
    private DnaStatisticsService dnaStatisticsService;
    
    @Mock
    private DnaAnalyzerRepository dnaAnalyzerRepository;
    
    @Test
    public void shouldReturnAllDnaChainsPersisted() {
        List<CadeiasDna> expectedCadeiasDnaList = DnaMocks.criaListaDeCadeiasDna();
        
        when(this.dnaAnalyzerRepository.findAll()).thenReturn(expectedCadeiasDnaList);
        
        List<CadeiasDna> actualCadeiasDnaList = this.dnaStatisticsService.buscaCadeiasDna();
        
        assertFalse(CollectionUtils.isEmpty(actualCadeiasDnaList));
        assertEquals(expectedCadeiasDnaList, actualCadeiasDnaList);
        verify(this.dnaAnalyzerRepository, times(1)).findAll();
    }
}
