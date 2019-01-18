package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.entities.Comuna;
import com.github.erodriguezg.springbootangular.entities.Provincia;
import com.github.erodriguezg.springbootangular.repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public Comuna traerPorId(Integer idComuna) {
        return comunaRepository.findById(idComuna).orElse(null);
    }

    public List<Comuna> traerPorProvincia(Provincia provincia) {
        return comunaRepository.findByProvincia(provincia);
    }

}
