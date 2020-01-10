package com.richardson.dna.controller;

import com.richardson.dna.application.DnaStatisticsApplication;
import com.richardson.dna.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats")
@Api(value = "DnaStatistics", description = "Estatísticas de Dna")
public class DnaStatisticsController {
    
    @Autowired
    private DnaStatisticsApplication dnaStatisticsApplication;
    
    @ApiOperation(value = "estatisticas", nickname = "Levantamento estatístico das cadeias de DNA")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> estatisticas() throws Exception {
        try {
            return new ResponseEntity<>(this.dnaStatisticsApplication.estatisticas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseUtils.criaResponseMessage(HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.name(),
                            e.getMessage(),
                            e.getClass().getSimpleName(),
                            "/stats"),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
