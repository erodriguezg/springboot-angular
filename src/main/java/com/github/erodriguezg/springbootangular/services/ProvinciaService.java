package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.entities.Region;
import com.github.erodriguezg.springbootangular.repository.ProvinciaRepository;
import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import com.github.erodriguezg.springbootangular.services.dto.RegionDto;
import com.github.erodriguezg.springbootangular.services.mappers.ProvinciaDtoMapper;
import com.github.erodriguezg.springbootangular.services.mappers.RegionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private RegionDtoMapper regionMapper;

    @Autowired
    private ProvinciaDtoMapper provinciaMapper;

    @Transactional(readOnly = true)
    public List<ProvinciaDto> traerPorRegion(RegionDto regionDto) {
        Region region = this.regionMapper.toEntidad(regionDto);
        return provinciaRepository.findByRegion(region).stream()
                .map(this.provinciaMapper::toDto)
                .collect(Collectors.toList());
    }

}
