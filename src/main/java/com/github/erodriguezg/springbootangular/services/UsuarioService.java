package com.github.erodriguezg.springbootangular.services;

import java.util.List;

import com.github.erodriguezg.springbootangular.services.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioFiltroDto;

public interface UsuarioService {

	long buscarRowCount(UsuarioFiltroDto filtroDto);

	List<UsuarioDto> buscar(UsuarioFiltroDto filtroDto, int inicio, int fin);

	List<UsuarioDto> buscar(UsuarioFiltroDto filterDto);

	void deshabilitar(UsuarioDto usuario, Long idUsuarioActual);

	void eliminar(UsuarioDto usuario, Long idPersonaIdentidad);

	UsuarioDto guardarUsuario(UsuarioDto usuario);

	void habilitar(UsuarioDto usuario);
	
	UsuarioDto traerPorId(Long idUsuario);

	UsuarioDto traerPorRun(Integer run);

	UsuarioDto traerPorUsername(String username);

	List<UsuarioDto> traerTodos();

	UsuarioDto traerPorEmail(String email);
}