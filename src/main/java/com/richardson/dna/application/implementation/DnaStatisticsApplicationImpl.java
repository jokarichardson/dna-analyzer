package com.richardson.dna.application.implementation;

import com.richardson.dna.application.DnaStatisticsApplication;
import com.richardson.dna.model.EstatisticasDna;
import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.service.DnaAnalyzerService;
import com.richardson.dna.service.DnaStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class DnaStatisticsApplicationImpl implements DnaStatisticsApplication {
    
    @Autowired
    private DnaStatisticsService dnaStatisticsService;
    
    @Autowired
    private DnaAnalyzerService dnaAnalyzerService;
    
    @Override
    public EstatisticasDna estatisticas() {
        EstatisticasDna estatisticasDna = new EstatisticasDna().toBuilder().build();
        
        List<CadeiasDna> cadeiasDna = this.dnaStatisticsService.buscaCadeiasDna();
        
        if (!CollectionUtils.isEmpty(cadeiasDna)) {
            cadeiasDna.forEach(cadeiaDna -> {
                if (this.dnaAnalyzerService.isSimian(cadeiaDna.getDna())) {
                    estatisticasDna.setCount_mutant_dna(estatisticasDna.getCount_mutant_dna() + 1);
                } else {
                    estatisticasDna.setCount_human_dna(estatisticasDna.getCount_human_dna() + 1);
                }
            });
            
            if (estatisticasDna.getCount_human_dna() > 0)
                estatisticasDna.setRatio((double) estatisticasDna.getCount_mutant_dna() / estatisticasDna.getCount_human_dna());
            else
                estatisticasDna.setRatio((double) estatisticasDna.getCount_mutant_dna());
        }
        
        return estatisticasDna;
    }
}
