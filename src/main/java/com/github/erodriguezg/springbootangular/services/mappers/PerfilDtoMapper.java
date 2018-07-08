package com.github.erodriguezg.springbootangular.services.mappers;

import org.springframework.stereotype.Component;

import com.github.erodriguezg.springbootangular.services.dto.PerfilDto;
import com.github.erodriguezg.springbootangular.services.entities.PerfilUsuario;

@Component
public class PerfilDtoMapper {

	public PerfilDto toPerfilDto(PerfilUsuario entidad) {
		PerfilDto dto = new PerfilDto();
		if (entidad != null) {
			dto.setId(entidad.getIdPerfilUsuario());
			dto.setNombre(entidad.getNombre());
		}
		return dto;
	}

	public PerfilUsuario toPerfilUsuario(PerfilDto dto) {
		PerfilUsuario entidad = new PerfilUsuario();
		if (dto == null) {
			return null;
		}
		entidad.setIdPerfilUsuario(dto.getId());
		entidad.setNombre(dto.getNombre());
		return entidad;
	}
}
