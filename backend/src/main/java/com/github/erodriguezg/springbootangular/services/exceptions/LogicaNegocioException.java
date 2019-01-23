package com.github.erodriguezg.springbootangular.services.exceptions;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class LogicaNegocioException extends RuntimeException {

	private static final long serialVersionUID = 8709051789853191269L;

	private final List<String> errores;

	public LogicaNegocioException(String message) {
		super(message);
		this.errores = new ArrayList<>();
	}

	public LogicaNegocioException(List<String> errores) {
		super(StringUtils.join(errores, ";"));
		this.errores = errores;
	}

	public List<String> getErrores() {
		return errores;
	}
}