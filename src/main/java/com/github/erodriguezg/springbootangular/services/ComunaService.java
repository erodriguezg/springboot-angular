package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.entities.Comuna;
import com.github.erodriguezg.springbootangular.entities.Provincia;
import com.github.erodriguezg.springbootangular.repository.ComunaRepository;
import com.github.erodriguezg.springbootangular.services.dto.ComunaDto;
import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import com.github.erodriguezg.springbootangular.services.mappers.ComunaDtoMapper;
import com.github.erodriguezg.springbootangular.services.mappers.ProvinciaDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ComunaDtoMapper comunaMapper;

    @Autowired
    private ProvinciaDtoMapper provinciaMapper;

    public ComunaDto traerPorId(Integer idComuna) {
        Comuna comuna = comunaRepository.findById(idComuna).orElse(null);
        return this.comunaMapper.toDto(comuna);
    }

    public List<ComunaDto> traerPorProvincia(ProvinciaDto provinciaDto) {
        Provincia provincia = this.provinciaMapper.toEntidad(provinciaDto);
        return comunaRepository.findByProvincia(provincia).stream()
                .map(this.comunaMapper::toDto)
                .collect(Collectors.toList());
    }

}
