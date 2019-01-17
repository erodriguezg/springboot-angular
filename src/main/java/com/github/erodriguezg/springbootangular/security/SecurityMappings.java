package com.github.erodriguezg.springbootangular.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.erodriguezg.springbootangular.entities.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SecurityMappings {

	public Map<String, String> userToTokenSubjectMap(Usuario usuario) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> sessionMap = new HashMap<>();
		try {
			sessionMap.put("usuario", mapper.writeValueAsString(usuario));
		} catch (JsonProcessingException ex) {
			throw new IllegalStateException(ex);
		}
		return sessionMap;
	}

	public Map<String, String> authToTokenSubjectMap(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		if (usuario == null) {
			return null;
		}
		return userToTokenSubjectMap(usuario);
	}

	public Authentication tokenSubjectMapToAuth(Map<String, String> subjectMap) {
		ObjectMapper mapper = new ObjectMapper();
		String usuarioJson = subjectMap.get("usuario");
		Usuario usuario;
		try {
			usuario = mapper.readValue(usuarioJson, Usuario.class);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
		return usuarioToAuth(usuario);
	}

	private Authentication usuarioToAuth(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(usuario.getPerfil().getNombre()));
		return new UsernamePasswordAuthenticationToken(usuario, null, authorities);
	}

}
