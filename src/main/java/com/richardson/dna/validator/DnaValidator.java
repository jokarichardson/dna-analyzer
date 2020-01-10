package com.richardson.dna.validator;

import com.richardson.dna.support.MessageUtils;
import com.richardson.dna.exception.DnaAnalyzerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

@Service
public class DnaValidator {
    
    private static final String CADEIA_INVALIDA_REGEX = "[^actgACTG]";
    
    @Autowired
    private MessageUtils messageUtils;
    
    public void validaCadeiaDna(String[] cadeiaDna) {
        this.validaNulidadeCadeia(cadeiaDna);
        this.validaBasesNitrogenadas(cadeiaDna);
        this.validaMatrizQuadrada(cadeiaDna);
    }
    
    private void validaNulidadeCadeia(String[] cadeiaDna) {
        Arrays.asList(cadeiaDna).forEach(cadeia -> {
            if (StringUtils.isEmpty(cadeia)) {
                throw new DnaAnalyzerException(this.messageUtils.get("msg.cadeia.branco"));
            }
        });
    }
    
    private void validaMatrizQuadrada(String[] cadeiasDna) {
        
        Arrays.asList(cadeiasDna).forEach(cadeiaDna -> {
            if (cadeiasDna.length != cadeiaDna.toCharArray().length){
                throw new DnaAnalyzerException(this.messageUtils.get("msg.matriz.nao.quadrada"));
            }
        });
    }
    
    private void validaBasesNitrogenadas(String[] cadeiaDna) {
        Pattern pattern = Pattern.compile(CADEIA_INVALIDA_REGEX);
        
        Arrays.asList(cadeiaDna).forEach(cadeia -> {
            if (pattern.matcher(cadeia).find())
                throw new DnaAnalyzerException(this.messageUtils.get("msg.cadeia.base.invalida"));
        });
    }
}
