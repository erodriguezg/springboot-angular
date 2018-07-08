package com.github.erodriguezg.springbootangular.services.dto;

import java.io.Serializable;

public class ComunaDto implements Serializable {

	private Integer idComuna;
	
	private String nombreComuna;

	public Integer getIdComuna() {
		return idComuna;
	}

	public void setIdComuna(Integer idComuna) {
		this.idComuna = idComuna;
	}

	public String getNombreComuna() {
		return nombreComuna;
	}

	public void setNombreComuna(String nombreComuna) {
		this.nombreComuna = nombreComuna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idComuna == null) ? 0 : idComuna.hashCode());
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
		if (!(obj instanceof ComunaDto)) {
			return false;
		}
		ComunaDto other = (ComunaDto) obj;
		if (idComuna == null) {
			if (other.idComuna != null) {
				return false;
			}
		} else if (!idComuna.equals(other.idComuna)) {
			return false;
		}
		return true;
	}

}
