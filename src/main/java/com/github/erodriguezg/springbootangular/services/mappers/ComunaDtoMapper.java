package com.github.erodriguezg.springbootangular.services.mappers;

import org.springframework.stereotype.Component;

import com.github.erodriguezg.springbootangular.services.dto.ComunaDto;
import com.github.erodriguezg.springbootangular.entities.Comuna;

@Component
public class ComunaDtoMapper {

	public ComunaDto toDto(Comuna entidad) {
		if (entidad == null) {
			return null;
		}
		ComunaDto dto = new ComunaDto();
		dto.setIdComuna(entidad.getId());
		dto.setNombreComuna(entidad.getNombre());
		return dto;
	}

	public Comuna toEntidad(ComunaDto dto) {
		if (dto == null) {
			return null;
		}
		Comuna entidad = new Comuna();
		entidad.setId(dto.getIdComuna());
		entidad.setNombre(dto.getNombreComuna());
		return entidad;
	}

}
