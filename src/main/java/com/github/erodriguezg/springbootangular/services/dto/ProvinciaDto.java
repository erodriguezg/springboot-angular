package com.github.erodriguezg.springbootangular.services.dto;

import java.io.Serializable;

public class ProvinciaDto implements Serializable {

	private Integer idProvincia;
	
	private String nombreProvincia;
	
	public ProvinciaDto() {
		//empty constructor
	}

	public ProvinciaDto(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Integer getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProvincia == null) ? 0 : idProvincia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProvinciaDto)) {
			return false;
		}
		ProvinciaDto other = (ProvinciaDto) obj;
		if (idProvincia == null) {
			if (other.idProvincia != null) {
				return false;
			}
		} else if (!idProvincia.equals(other.idProvincia)) {
			return false;
		}
		return true;
	}

}