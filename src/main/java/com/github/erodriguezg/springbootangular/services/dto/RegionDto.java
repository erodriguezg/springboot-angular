package com.github.erodriguezg.springbootangular.services.dto;

import java.io.Serializable;

public class RegionDto implements Serializable {

	private static final long serialVersionUID = -1899674472387828113L;

	private Integer idRegion;
	
	private String nombreRegion;
	
	public RegionDto() {
		//constructor vacio
	}
	
	public RegionDto(Integer idRegion) {
		this.idRegion = idRegion;
	}

	public Integer getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}

	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRegion == null) ? 0 : idRegion.hashCode());
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
		if (!(obj instanceof RegionDto)) {
			return false;
		}
		RegionDto other = (RegionDto) obj;
		if (idRegion == null) {
			if (other.idRegion != null) {
				return false;
			}
		} else if (!idRegion.equals(other.idRegion)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RegionDto [idRegion=" + idRegion + ", nombreRegion=" + nombreRegion + "]";
	}
	
}
