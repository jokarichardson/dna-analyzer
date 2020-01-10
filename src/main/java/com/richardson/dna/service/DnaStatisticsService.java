package com.richardson.dna.service;

import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.repository.DnaAnalyzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DnaStatisticsService {
    
    @Autowired
    private DnaAnalyzerRepository dnaAnalyzerRepository;
    
    public List<CadeiasDna> buscaCadeiasDna() {
        return this.dnaAnalyzerRepository.findAll();
    }
}
