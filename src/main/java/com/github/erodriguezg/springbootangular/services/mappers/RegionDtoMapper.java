package com.github.erodriguezg.springbootangular.services.mappers;

import org.springframework.stereotype.Component;

import com.github.erodriguezg.springbootangular.services.dto.RegionDto;
import com.github.erodriguezg.springbootangular.services.entities.Region;

@Component
public class RegionDtoMapper {

	public RegionDto toDto(Region entidad) {
		if(entidad == null) {
			return null;
		}
		RegionDto dto = new RegionDto();
		dto.setIdRegion(entidad.getId());
		dto.setNombreRegion(entidad.getNombre());
		return dto;
	}
	
	public Region toEntidad(RegionDto dto) {
		if(dto == null) {
			return null;
		}
		Region entidad = new Region();
		entidad.setId(dto.getIdRegion());
		entidad.setNombre(dto.getNombreRegion());
		return entidad;
	}
	
}
