package com.github.erodriguezg.springbootangular.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldRest {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

}
