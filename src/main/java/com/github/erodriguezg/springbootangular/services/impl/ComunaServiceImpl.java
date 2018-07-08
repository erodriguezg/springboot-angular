package com.github.erodriguezg.springbootangular.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.erodriguezg.springbootangular.services.ComunaService;
import com.github.erodriguezg.springbootangular.services.dao.ComunaDao;
import com.github.erodriguezg.springbootangular.services.dto.ComunaDto;
import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import com.github.erodriguezg.springbootangular.services.entities.Comuna;
import com.github.erodriguezg.springbootangular.services.entities.Provincia;
import com.github.erodriguezg.springbootangular.services.mappers.ComunaDtoMapper;
import com.github.erodriguezg.springbootangular.services.mappers.ProvinciaDtoMapper;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class ComunaServiceImpl implements ComunaService {

	@Autowired
	private ComunaDao comunaDao;
	
	@Autowired
	private ComunaDtoMapper comunaMapper;
	
	@Autowired
	private ProvinciaDtoMapper provinciaMapper;
	
	@Transactional(readOnly = true)
	@Override
	public ComunaDto traerPorId(Integer idComuna) {
		Comuna comuna =  comunaDao.traerPorId(idComuna);
		return this.comunaMapper.toDto(comuna);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ComunaDto> traerPorProvincia(ProvinciaDto provinciaDto) {
		Provincia provincia = this.provinciaMapper.toEntidad(provinciaDto);
		return comunaDao.traerPorProvincia(provincia).stream()
				.map(this.comunaMapper::toDto)
				.collect(Collectors.toList());
	}

}
