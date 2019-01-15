package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.repository.RegionRepository;
import com.github.erodriguezg.springbootangular.services.dto.RegionDto;
import com.github.erodriguezg.springbootangular.services.mappers.RegionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionDtoMapper regionMapper;

    @Transactional(readOnly = true)
    public List<RegionDto> traerTodas() {
        return regionRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"))
                .stream()
                .map(region -> regionMapper.toDto(region))
                .collect(Collectors.toList());
    }

}
