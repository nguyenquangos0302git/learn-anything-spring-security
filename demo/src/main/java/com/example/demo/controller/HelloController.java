package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloController {

    @GetMapping("/tenantAuth")
    public String tenantAuth() {
        return "tenantAuth";
    }

    @GetMapping("/basicAuth")
    public String basicAuth() {
        return "basicAuth";
    }

    @GetMapping("/jwtAuth")
    public String jwtAuth() {
        return "jwtAuth";
    }

    @GetMapping("/noauth")
    public String noAuth() {
        return "no auth";
    }

}
