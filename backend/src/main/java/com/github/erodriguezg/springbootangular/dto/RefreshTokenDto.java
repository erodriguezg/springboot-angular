package com.github.erodriguezg.springbootangular.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefreshTokenDto implements Serializable {

    private static final long serialVersionUID = -640367296525474994L;

    private String token;

}
