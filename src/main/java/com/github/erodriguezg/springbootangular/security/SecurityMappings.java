package com.github.erodriguezg.springbootangular.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.erodriguezg.springbootangular.dto.UsuarioDto;

public class SecurityMappings {

	public Map<String, String> userToTokenSubjectMap(UsuarioDto usuarioDto) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> sessionMap = new HashMap<>();
		try {
			sessionMap.put("usuario", mapper.writeValueAsString(usuarioDto));
		} catch (JsonProcessingException ex) {
			throw new IllegalStateException(ex);
		}
		return sessionMap;
	}

	public Map<String, String> authToTokenSubjectMap(Authentication authentication) {
		UsuarioDto usuario = (UsuarioDto) authentication.getPrincipal();
		if (usuario == null) {
			return null;
		}
		return userToTokenSubjectMap(usuario);
	}

	public Authentication tokenSubjectMapToAuth(Map<String, String> subjectMap) {
		ObjectMapper mapper = new ObjectMapper();
		String usuarioJson = subjectMap.get("usuario");
		UsuarioDto usuarioDto;
		try {
			usuarioDto = mapper.readValue(usuarioJson, UsuarioDto.class);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
		return usuarioToAuth(usuarioDto);
	}

	private Authentication usuarioToAuth(UsuarioDto usuarioDto) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		//authorities.add(new SimpleGrantedAuthority(usuarioDto.getPerfil().getNombre()));
		return new UsernamePasswordAuthenticationToken(usuarioDto, null, authorities);
	}

}
