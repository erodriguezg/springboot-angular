package com.github.erodriguezg.springbootangular.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class CredencialesDto implements Serializable {

	private static final long serialVersionUID = -7461332674915728352L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
