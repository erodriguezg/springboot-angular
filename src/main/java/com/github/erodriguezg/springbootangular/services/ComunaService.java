package com.github.erodriguezg.springbootangular.services;

import java.util.List;

import com.github.erodriguezg.springbootangular.services.dto.ComunaDto;
import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;

public interface ComunaService {

	ComunaDto traerPorId(Integer idComuna);

	List<ComunaDto> traerPorProvincia(ProvinciaDto provinciaDto);

}
