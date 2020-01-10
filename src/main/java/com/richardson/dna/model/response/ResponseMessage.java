package com.richardson.dna.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class ResponseMessage {
    private Date timestamp;
    private Integer status;
    private String error;
    private String exception;
    private String message;
    private String path;
}