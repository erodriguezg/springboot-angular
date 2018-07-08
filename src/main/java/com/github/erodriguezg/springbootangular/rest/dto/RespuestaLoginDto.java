package com.github.erodriguezg.springbootangular.rest.dto;

import java.io.Serializable;
import java.util.List;

public class RespuestaLoginDto implements Serializable {

	private static final long serialVersionUID = -140583251789279571L;

	private String token;

    private Boolean exitoLogin;

    private List<String> errores;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getExitoLogin() {
        return exitoLogin;
    }

    public void setExitoLogin(Boolean exitoLogin) {
        this.exitoLogin = exitoLogin;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
}
