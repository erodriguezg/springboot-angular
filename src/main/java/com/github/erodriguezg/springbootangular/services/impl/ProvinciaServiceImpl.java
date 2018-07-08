package com.github.erodriguezg.springbootangular.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.erodriguezg.springbootangular.services.ProvinciaService;
import com.github.erodriguezg.springbootangular.services.dao.ProvinciaDao;
import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import com.github.erodriguezg.springbootangular.services.dto.RegionDto;
import com.github.erodriguezg.springbootangular.services.entities.Region;
import com.github.erodriguezg.springbootangular.services.mappers.ProvinciaDtoMapper;
import com.github.erodriguezg.springbootangular.services.mappers.RegionDtoMapper;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class ProvinciaServiceImpl implements ProvinciaService {

	@Autowired
	private RegionDtoMapper regionMapper;
	
	@Autowired
	private ProvinciaDtoMapper provinciaMapper;

	@Autowired
	private ProvinciaDao provinciaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<ProvinciaDto> traerPorRegion(RegionDto regionDto) {
		Region region = this.regionMapper.toEntidad(regionDto);
		return provinciaDao.traerPorRegion(region).stream()
				.map(this.provinciaMapper::toDto)
				.collect(Collectors.toList());
	}

}
