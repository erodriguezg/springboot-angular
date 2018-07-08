package com.github.erodriguezg.springbootangular.rest.impl;

import com.github.erodriguezg.springbootangular.rest.HelloworldRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldRestImpl implements HelloworldRest {

    @GetMapping("/hello")
    @Override
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/hello-protected")
    @Override
    public String helloProtected() {
        return "Hello World Protected";
    }

}
