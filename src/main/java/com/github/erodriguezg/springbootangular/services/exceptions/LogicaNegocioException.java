package com.github.erodriguezg.springbootangular.services.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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