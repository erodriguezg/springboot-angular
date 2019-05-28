package com.github.erodriguezg.springbootangular.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RespuestaLoginDto implements Serializable {

    private static final long serialVersionUID = -140583251789279571L;

    private String token;

    private Boolean exitoLogin;

    private List<String> errores;

}
