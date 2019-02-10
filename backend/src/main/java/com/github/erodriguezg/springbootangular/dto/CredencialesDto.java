package com.github.erodriguezg.springbootangular.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CredencialesDto implements Serializable {

    private static final long serialVersionUID = -7461332674915728352L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
