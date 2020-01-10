package com.richardson.dna.mocks;

import com.richardson.dna.model.CadeiasDna;

import java.util.ArrayList;
import java.util.List;

public class DnaMocks {
    
    private static final String[] CADEIA_DNA_SIMIO_LINHA = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
    private static final String[] CADEIA_DNA_SIMIO_COLUNA = {"CTGAGA", "CTGAGC", "CATTGT", "CGAGAG", "ACCCTA", "TCACTG"};
    private static final String[] CADEIA_DNA_SIMIO_DIAGONAL_PRINCIPAL = {"CTGCGA", "CTAAGC", "TAATCT", "ATAGGG", "CCTCTA", "TCATTG"};
    private static final String[] CADEIA_DNA_SIMIO_DIAGONAL_SECUNDARIA = {"CTGAGA", "CTAAGC", "TAATCT", "ATAGGG", "CCGCTA", "TCACTG"};
    private static final String[] CADEIA_DNA_SIMIO_DIAGONAL_ACIMA_DA_PRINCIPAL = {"CTGCGA", "CTTAGC", "AAATCT", "ATAGTG", "CCTCTA", "TCATTG"};
    private static final String[] CADEIA_DNA_SIMIO_DIAGONAL_ABAIXO_DA_SECUNDARIA = {"CTGAGA", "CTAAGC", "TAATCT", "TTACGG", "CACCTA", "TCACTG"};
    private static final String[] CADEIA_DNA_HUMANO = {"ACTTGA", "CTTGAC", "TGATCT", "GTATGG", "GCTCTA", "TCAGTG"};
    private static final String[] CADEIA_DNA_INVALIDA = {"ZCTTGA", "CTTGAC", "TGATCT", "GTATGG", "GCTCTA", "TCAGTG"};
    private static final String[] CADEIA_DNA_INVALIDA_NAO_QUADRADA = {"CTTGA", "CTTGAC", "TGATCT", "GTATGG", "GCTCTA", "TCAGTG"};
    private static final String[] CADEIA_DNA_INVALIDA_CADEIA_VAZIA = {"", "CTTGAC", "TGATCT", "GTATGG", "GCTCTA", "TCAGTG"};
    
    
    public static CadeiasDna criaCadeiasDnaSimioLinha() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_SIMIO_LINHA)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaSimioColuna() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_SIMIO_COLUNA)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaSimioDiagonalPrincipal() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_SIMIO_DIAGONAL_PRINCIPAL)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaSimioDiagonalAcimaDaPrincipal() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_SIMIO_DIAGONAL_ACIMA_DA_PRINCIPAL)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaSimioDiagonalSecundaria() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_SIMIO_DIAGONAL_SECUNDARIA)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaSimioDiagonalAbaixoDaSecundaria() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_SIMIO_DIAGONAL_ABAIXO_DA_SECUNDARIA)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaHumano() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_HUMANO)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaInvalida() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_INVALIDA)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaInvalidaNaoQuadrada() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_INVALIDA_NAO_QUADRADA)
                .build();
    }
    
    public static CadeiasDna criaCadeiasDnaInvalidaComVazio() {
        return CadeiasDna.builder()
                .dna(CADEIA_DNA_INVALIDA_CADEIA_VAZIA)
                .build();
    }
    
    public static List<CadeiasDna> criaListaDeCadeiasDna() {
        List<CadeiasDna> cadeiasDnaList = new ArrayList<>();
        cadeiasDnaList.add(criaCadeiasDnaHumano());
        cadeiasDnaList.add(criaCadeiasDnaSimioColuna());
        cadeiasDnaList.add(criaCadeiasDnaSimioLinha());
        
        return cadeiasDnaList;
    }
}
