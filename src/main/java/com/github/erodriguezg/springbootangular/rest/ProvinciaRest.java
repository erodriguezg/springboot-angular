package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;

import java.util.List;

public interface ProvinciaRest {

	List<ProvinciaDto> traerPorIdRegion(Integer idRegion);

}
