package com.richardson.dna.application;

import com.richardson.dna.application.implementation.DnaStatisticsApplicationImpl;
import com.richardson.dna.mocks.DnaMocks;
import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.model.EstatisticasDna;
import com.richardson.dna.service.DnaAnalyzerService;
import com.richardson.dna.service.DnaStatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DnaStatisticsApplicationTest {
    
    @InjectMocks
    private DnaStatisticsApplicationImpl dnaStatisticsApplication;
    
    @Mock
    private DnaStatisticsService dnaStatisticsService;
    
    @Mock
    private DnaAnalyzerService dnaAnalyzerService;
    
    @Test
    public void givenARequestForStatisticsShouldReturnOnlyHumanData() {
        List<CadeiasDna> cadeiasDnaList = Collections.singletonList(DnaMocks.criaCadeiasDnaHumano());
        
        when(this.dnaStatisticsService.buscaCadeiasDna()).thenReturn(cadeiasDnaList);
        when(this.dnaAnalyzerService.isSimian(any())).thenReturn(Boolean.FALSE);
    
        EstatisticasDna estatisticasDna = this.dnaStatisticsApplication.estatisticas();
    
        assertNotNull(estatisticasDna);
        assertTrue(estatisticasDna.getCount_mutant_dna() == 0);
        assertTrue(estatisticasDna.getCount_human_dna() > 0);
        assertTrue(estatisticasDna.getCount_human_dna() == 1);
        assertTrue(estatisticasDna.getRatio() == 0.0D);
        
        verify(this.dnaStatisticsService, times(1)).buscaCadeiasDna();
    }
    
    @Test
    public void givenARequestForStatisticsShouldReturnOnlyMutantData() {
        List<CadeiasDna> cadeiasDnaList = Collections.singletonList(DnaMocks.criaCadeiasDnaSimioDiagonalPrincipal());
        
        when(this.dnaStatisticsService.buscaCadeiasDna()).thenReturn(cadeiasDnaList);
        when(this.dnaAnalyzerService.isSimian(any())).thenReturn(Boolean.TRUE);
        
        EstatisticasDna estatisticasDna = this.dnaStatisticsApplication.estatisticas();
        
        assertNotNull(estatisticasDna);
        assertTrue(estatisticasDna.getCount_mutant_dna() > 0);
        assertTrue(estatisticasDna.getCount_mutant_dna() == 1);
        assertTrue(estatisticasDna.getCount_human_dna() == 0);
        assertTrue(estatisticasDna.getRatio() == 1.0D);
        
        verify(this.dnaStatisticsService, times(1)).buscaCadeiasDna();
    }
    
    @Test
    public void givenARequestForStatisticsShouldReturnEmptyData() {
        when(this.dnaStatisticsService.buscaCadeiasDna()).thenReturn(Collections.emptyList());
        when(this.dnaAnalyzerService.isSimian(any())).thenReturn(Boolean.TRUE);
        
        EstatisticasDna estatisticasDna = this.dnaStatisticsApplication.estatisticas();
        
        assertNotNull(estatisticasDna);
        assertTrue(estatisticasDna.getCount_mutant_dna() == 0);
        assertTrue(estatisticasDna.getCount_mutant_dna() == 0);
        assertTrue(estatisticasDna.getCount_human_dna() == 0);
        assertTrue(estatisticasDna.getRatio() == 0.0D);
        
        verify(this.dnaStatisticsService, times(1)).buscaCadeiasDna();
    }
}
