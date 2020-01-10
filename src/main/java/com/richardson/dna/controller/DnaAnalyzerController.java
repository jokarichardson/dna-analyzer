package com.richardson.dna.controller;

import com.richardson.dna.application.DnaAnalyzerApplication;
import com.richardson.dna.application.DnaStatisticsApplication;
import com.richardson.dna.model.CadeiasDna;
import com.richardson.dna.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/simian")
@Api(value = "DnaAnalyzer", description = "Analisador de cadeias de nucleotídeos")
public class DnaAnalyzerController {
    
    @Autowired
    private DnaAnalyzerApplication dnaAnalyzerApplication;
    
    @Autowired
    private DnaStatisticsApplication dnaStatisticsApplication;
    
    @ApiOperation(value = "acessar", nickname = "Controlador de Acesso por DNA")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> acessar(@RequestBody @Valid CadeiasDna cadeiasDna) throws Exception {
        try {
            return (this.dnaAnalyzerApplication.isSimian(cadeiasDna) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.FORBIDDEN));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseUtils.criaResponseMessage(HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.name(),
                            e.getMessage(),
                            e.getClass().getSimpleName(),
                            "/simian"),
                    HttpStatus.BAD_REQUEST);
        }
    }
}