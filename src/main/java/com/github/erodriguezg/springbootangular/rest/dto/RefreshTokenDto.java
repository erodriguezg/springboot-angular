package com.github.erodriguezg.springbootangular.rest.dto;

import java.io.Serializable;

public class RefreshTokenDto implements Serializable {

	private static final long serialVersionUID = -640367296525474994L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
