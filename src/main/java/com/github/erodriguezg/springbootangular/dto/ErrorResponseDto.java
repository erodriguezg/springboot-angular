package com.github.erodriguezg.springbootangular.dto;

import java.io.Serializable;
import java.util.Map;

public class ErrorResponseDto implements Serializable {

    private String errorType;

    private String message;

    private Map<String,Serializable> values;

    public Map<String, Serializable> getValues() {
        return values;
    }

    public void setValues(Map<String, Serializable> values) {
        this.values = values;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
