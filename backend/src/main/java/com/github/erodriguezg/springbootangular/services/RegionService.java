package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.entities.Region;
import com.github.erodriguezg.springbootangular.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Transactional(readOnly = true)
    public List<Region> traerTodas() {
        return regionRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

}
