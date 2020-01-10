package com.richardson.dna.support;

import com.richardson.dna.model.response.ResponseMessage;

import java.util.Date;

public class ResponseUtils {
    
    public static ResponseMessage criaResponseMessage(Integer status, String error, String message, String exceptionClass, String mapping) {
        return ResponseMessage.builder()
                .timestamp(new Date(System.currentTimeMillis()))
                .status(status)
                .error(error)
                .exception(exceptionClass)
                .message(message)
                .path(mapping)
                .build();
    }
}
