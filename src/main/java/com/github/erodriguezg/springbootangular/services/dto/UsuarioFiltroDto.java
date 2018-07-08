package com.github.erodriguezg.springbootangular.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class UsuarioFiltroDto implements Serializable {

	private static final long serialVersionUID = -7369622710383099279L;

	private String username;
	private Integer run;
	private String nombres;
	private String apPaterno;
	private String apMaterno;
	private String email;
	private Date fechaNacimientoInferior;
	private Date fechaNacimientoSuperior;
	private Boolean habilitado;
	
	@JsonProperty(value = "perfil")
	private PerfilDto perfilDto;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimientoInferior() {
		return fechaNacimientoInferior;
	}

	public void setFechaNacimientoInferior(Date fechaNacimientoInferior) {
		this.fechaNacimientoInferior = fechaNacimientoInferior;
	}

	public Date getFechaNacimientoSuperior() {
		return fechaNacimientoSuperior;
	}

	public void setFechaNacimientoSuperior(Date fechaNacimientoSuperior) {
		this.fechaNacimientoSuperior = fechaNacimientoSuperior;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public PerfilDto getPerfilDto() {
		return perfilDto;
	}

	public void setPerfilDto(PerfilDto perfilDto) {
		this.perfilDto = perfilDto;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public Integer getRun() {
		return run;
	}

	public void setRun(Integer run) {
		this.run = run;
	}
}
