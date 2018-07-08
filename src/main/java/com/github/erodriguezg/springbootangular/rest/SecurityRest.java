package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.rest.dto.CredencialesDto;
import com.github.erodriguezg.springbootangular.rest.dto.RefreshTokenDto;
import com.github.erodriguezg.springbootangular.rest.dto.RespuestaLoginDto;
import org.springframework.validation.BindingResult;

public interface SecurityRest {

    RespuestaLoginDto login(CredencialesDto credenciales, BindingResult bindResult);

    RefreshTokenDto refreshToken();

}
