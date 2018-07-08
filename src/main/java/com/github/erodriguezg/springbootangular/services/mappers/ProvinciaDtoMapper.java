package com.github.erodriguezg.springbootangular.services.mappers;

import org.springframework.stereotype.Component;

import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import com.github.erodriguezg.springbootangular.services.entities.Provincia;

@Component
public class ProvinciaDtoMapper {

	public ProvinciaDto toDto(Provincia entidad) {
		if (entidad == null) {
			return null;
		}
		ProvinciaDto dto = new ProvinciaDto();
		dto.setIdProvincia(entidad.getId());
		dto.setNombreProvincia(entidad.getNombre());
		return dto;
	}

	public Provincia toEntidad(ProvinciaDto dto) {
		if (dto == null) {
			return null;
		}
		Provincia entidad = new Provincia();
		entidad.setId(dto.getIdProvincia());
		entidad.setNombre(dto.getNombreProvincia());
		return entidad;
	}

}
