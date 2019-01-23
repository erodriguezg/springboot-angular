package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.entities.Provincia;
import com.github.erodriguezg.springbootangular.entities.Region;
import com.github.erodriguezg.springbootangular.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Transactional(readOnly = true)
    public List<Provincia> traerPorRegion(Region region) {
        return provinciaRepository.findByRegion(region);
    }

}
