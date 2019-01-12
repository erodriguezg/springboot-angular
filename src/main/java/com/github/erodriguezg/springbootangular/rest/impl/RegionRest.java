package com.github.erodriguezg.springbootangular.rest.impl;

import com.github.erodriguezg.springbootangular.services.RegionService;
import com.github.erodriguezg.springbootangular.services.dto.RegionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regiones")
public class RegionRest {

    @Autowired
    private RegionService regionService;

    @GetMapping("/todas")
    @PreAuthorize("isAuthenticated()")
    public List<RegionDto> traerTodas() {
        return regionService.traerTodas();
    }

}
