package com.richardson.dna.service;

import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.repository.DnaAnalyzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class DnaAnalyzerService {
    
    private static final String CADEIA_MUTANTE_REGEX = "([acgtACGT])\\1{3}";
    
    @Autowired
    private DnaAnalyzerRepository dnaAnalyzerRepository;
    
    public Boolean isSimian(String[] dna) {
        return this.avaliarLinhasMatrizDna(this.converteEmMatrizDna(dna)) ||
                this.avaliarColunasMatrizDna(this.converteEmMatrizDna(dna)) ||
                this.avaliarDiagonaisMatrizDna(this.converteEmMatrizDna(dna));
    }
    
    public void salvarCadeiasDna(CadeiasDna cadeiasDna) {
        this.dnaAnalyzerRepository.save(cadeiasDna);
    }
    
    public List<CadeiasDna> buscaCadeiasDnaPersistidas(String[] cadeiaDna) {
        return this.dnaAnalyzerRepository.findByCondition(Arrays.asList(cadeiaDna));
    }
    
    private String[][] converteEmMatrizDna(String[] cadeiasDna) {
        String[][] matrizDna = new String[cadeiasDna.length][cadeiasDna.length];
        List<String> basesNitrogenadas = new ArrayList<>();
        int i = 0;
        int j = 0;
        
        for (String cadeiaDna : cadeiasDna) {
            for (String baseNitrogenada : cadeiaDna.split("")) {
                matrizDna[i][j] = baseNitrogenada;
                j++;
            }
            if (j > cadeiasDna.length - 1) {
                i++;
                j = 0;
            }
        }
        
        return matrizDna;
    }
    
    private Boolean avaliarLinhasMatrizDna(String[][] matrizDna) {
        StringBuilder cadeiasLinha = new StringBuilder();
        Pattern pattern = Pattern.compile(CADEIA_MUTANTE_REGEX);
        
        for (int linha = 0; linha < matrizDna.length; linha++) {
            for (int coluna = 0; coluna < matrizDna[linha].length; coluna++) {
                cadeiasLinha.append(matrizDna[linha][coluna]);
            }
            if (pattern.matcher(cadeiasLinha).find())
                return Boolean.TRUE;
            
            cadeiasLinha.setLength(0);
        }
        
        return Boolean.FALSE;
    }
    
    private Boolean avaliarColunasMatrizDna(String[][] matrizDna) {
        StringBuilder cadeiasColuna = new StringBuilder();
        Pattern pattern = Pattern.compile(CADEIA_MUTANTE_REGEX);
        
        for (int coluna = 0; coluna < matrizDna.length; coluna++) {
            for (int linha = 0; linha < matrizDna[coluna].length; linha++) {
                cadeiasColuna.append(matrizDna[linha][coluna]);
            }
            if (pattern.matcher(cadeiasColuna).find())
                return Boolean.TRUE;
            
            cadeiasColuna.setLength(0);
        }
        
        return Boolean.FALSE;
    }
    
    private Boolean avaliarDiagonaisMatrizDna(String[][] matrizDna) {
        return this.avaliarDiagonaisPrincipais(matrizDna) || this.avaliarDiagonaisSecundarias(matrizDna);
    }
    
    private Boolean avaliarDiagonaisPrincipais(String[][] matrizDna) {
        StringBuilder bases = new StringBuilder();
        Pattern pattern = Pattern.compile(CADEIA_MUTANTE_REGEX);
        
        for (int i = (matrizDna.length - 1); i > 0; i--) {
            for (int coluna = 0, linha = i; linha <= (matrizDna.length - 1); coluna++, linha++) {
                bases.append(matrizDna[linha][coluna]);
            }
            
            if (pattern.matcher(bases).find())
                return Boolean.TRUE;
            
            bases.setLength(0);
        }
        
        bases.setLength(0);
        
        for (int i = 0; i <= (matrizDna.length - 1); i++) {
            for (int linha = 0, coluna = i; coluna <= (matrizDna.length - 1); linha++, coluna++) {
                bases.append(matrizDna[linha][coluna]);
            }
            
            if (pattern.matcher(bases).find())
                return Boolean.TRUE;
            
            bases.setLength(0);
        }
        
        return Boolean.FALSE;
    }
    
    private Boolean avaliarDiagonaisSecundarias(String[][] matrizDna) {
        Pattern pattern = Pattern.compile(CADEIA_MUTANTE_REGEX);
        StringBuilder bases = new StringBuilder();
        
        int qtdDiagonaisSecundarias = (matrizDna.length * 2) - 1;
        int elementosDiagonal = 0;
        int centro = (qtdDiagonaisSecundarias / 2) + 1;
        int linha;
        int coluna;
        
        for (int i = 1; i <= qtdDiagonaisSecundarias; i++) {
            if (i <= centro) {
                elementosDiagonal++;
                for (int j = 0; j < elementosDiagonal; j++) {
                    linha = (i - j) - 1;
                    coluna = j;
                    bases.append(matrizDna[linha][coluna]);
                }
            } else {
                elementosDiagonal--;
                for (int j = 0; j < elementosDiagonal; j++) {
                    linha = (matrizDna.length - 1) - j;
                    coluna = (i - matrizDna.length) + j;
                    bases.append(matrizDna[linha][coluna]);
                }
            }
            
            if (i != qtdDiagonaisSecundarias) {
                if (pattern.matcher(bases).find())
                    return Boolean.TRUE;
                
                bases.setLength(0);
            }
        }
        
        return Boolean.FALSE;
    }
}
