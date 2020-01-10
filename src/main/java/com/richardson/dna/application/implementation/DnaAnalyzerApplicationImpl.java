package com.richardson.dna.application.implementation;

import com.richardson.dna.application.DnaAnalyzerApplication;
import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.service.DnaAnalyzerService;
import com.richardson.dna.validator.DnaValidator;
import com.richardson.dna.service.DnaStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class DnaAnalyzerApplicationImpl implements DnaAnalyzerApplication {
    
    @Autowired
    private DnaValidator dnaValidator;
    
    @Autowired
    private DnaAnalyzerService dnaAnalyzerService;
    
    @Autowired
    private DnaStatisticsService dnaStatisticsService;
    
    @Override
    public Boolean isSimian(CadeiasDna cadeiasDna) {
        this.dnaValidator.validaCadeiaDna(cadeiasDna.getDna());
        Boolean isSimian = this.dnaAnalyzerService.isSimian(cadeiasDna.getDna());
        
        List<CadeiasDna> cadeiasDnaList = this.dnaAnalyzerService.buscaCadeiasDnaPersistidas(cadeiasDna.getDna());
        
        if (CollectionUtils.isEmpty(cadeiasDnaList))
            this.dnaAnalyzerService.salvarCadeiasDna(cadeiasDna);
        
        return isSimian;
    }
}