package com.github.erodriguezg.springbootangular.utils;

import com.github.erodriguezg.springbootangular.security.Identidad;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public Identidad getActualIdentidad() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() != null) {
            return (Identidad) authentication.getPrincipal();
        }
        return null;
    }

}
