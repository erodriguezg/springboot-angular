package com.github.erodriguezg.springbootangular.services.dto;

import java.io.Serializable;
import java.util.Objects;

public class PerfilDto implements Serializable {

	private static final long serialVersionUID = 7388061855355772080L;

	private Integer id;

	private String nombre;

	public PerfilDto() {
		// vacio
	}

	public PerfilDto(Integer id) {
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PerfilDto perfil = (PerfilDto) o;
		return Objects.equals(this.id, perfil.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

}
