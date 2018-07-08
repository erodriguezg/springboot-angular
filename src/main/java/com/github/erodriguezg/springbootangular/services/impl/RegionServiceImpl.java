package com.github.erodriguezg.springbootangular.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.erodriguezg.springbootangular.services.RegionService;
import com.github.erodriguezg.springbootangular.services.dao.RegionDao;
import com.github.erodriguezg.springbootangular.services.dto.RegionDto;
import com.github.erodriguezg.springbootangular.services.mappers.RegionDtoMapper;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionDtoMapper regionMapper;

	@Autowired
	private RegionDao regionDao;

	@Transactional(readOnly = true)
	@Override
	public List<RegionDto> traerTodas() {
		return this.regionDao.traerTodas().stream().map(this.regionMapper::toDto).collect(Collectors.toList());
	}

}
