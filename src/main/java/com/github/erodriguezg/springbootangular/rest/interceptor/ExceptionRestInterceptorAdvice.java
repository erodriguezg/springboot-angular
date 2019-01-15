package com.github.erodriguezg.springbootangular.rest.interceptor;

import com.github.erodriguezg.springbootangular.dto.ErrorResponseDto;
import com.github.erodriguezg.springbootangular.services.exceptions.LogicaNegocioException;
import com.github.erodriguezg.springbootangular.utils.ConstantesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionRestInterceptorAdvice {

    private static Logger log = LoggerFactory.getLogger(ExceptionRestInterceptorAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> exceptionHandler(Exception ex) {
        log.error("Error Interno: ", ex);
        ErrorResponseDto error = new ErrorResponseDto();
        error.setErrorType("ErrorInterno");
        error.setMessage(ConstantesUtil.MSJ_ERROR_INTERNO);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> AccessDeniedHandler(AccessDeniedException ex) {
        log.warn("Acceso Denegado: ", ex);
        ErrorResponseDto error = new ErrorResponseDto();
        error.setErrorType("AccessDenied");
        error.setMessage("Acceso Denegado");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LogicaNegocioException.class)
    public ResponseEntity<ErrorResponseDto> logicaNegocioHandler(LogicaNegocioException ex) {
        log.debug("Logica Negocio Exception: ", ex);
        ErrorResponseDto error = new ErrorResponseDto();
        error.setErrorType(ex.getClass().getSimpleName());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
