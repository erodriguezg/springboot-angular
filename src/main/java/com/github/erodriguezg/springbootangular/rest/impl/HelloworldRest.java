package com.github.erodriguezg.springbootangular.rest.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldRest {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/hello-protected")
    public String helloProtected() {
        return "Hello World Protected";
    }

}
