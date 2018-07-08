package com.github.erodriguezg.springbootangular.services;

import java.util.List;

import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import com.github.erodriguezg.springbootangular.services.dto.RegionDto;

public interface ProvinciaService {
	
	List<ProvinciaDto> traerPorRegion(RegionDto regionDto);

}
