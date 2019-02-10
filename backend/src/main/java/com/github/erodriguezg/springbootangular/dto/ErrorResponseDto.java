package com.github.erodriguezg.springbootangular.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ErrorResponseDto implements Serializable {

    private String errorType;

    private String message;

    private Map<String, Serializable> values;

}
