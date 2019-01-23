package com.github.erodriguezg.springbootangular.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/version")
public class VersionRest {

    @Value("${app.version}")
    private String version;

    @GetMapping
    public Map<String, String> getVersion() {
        Map<String, String> map = new HashMap<>();
        map.put("version", version);
        return map;
    }

}
