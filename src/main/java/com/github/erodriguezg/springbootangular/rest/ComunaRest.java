package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.services.dto.ComunaDto;

import java.util.List;

public interface ComunaRest {

    ComunaDto traerComunaPorIdComuna(Integer idComuna);

    List<ComunaDto> traerComunasPorProvincia(int idProvincia);

}
