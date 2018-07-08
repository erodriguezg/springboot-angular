package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.services.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioFiltroDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UsuarioRest {

    UsuarioDto traerPorId(Long idUsuario);

    UsuarioDto traerPorUsername(String username);

    UsuarioDto taerPorEmail(String email);

    UsuarioDto traerPorRun(Integer run);

    long buscarRowCount(UsuarioFiltroDto filtros);

    List<UsuarioDto> buscar(UsuarioFiltroDto filtros, int inicio, int fin);

    List<UsuarioDto> buscar(UsuarioFiltroDto filtros);

    void guardar(UsuarioDto usuarioDto, BindingResult bindResult, UsuarioDto principal);

    void eliminar(Long idUsuario, UsuarioDto principal);

    void cambiarPass(Long idUsuario, String newPass);

}
