package com.dasza.productmgmt.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/unsecured")
    public String unsecuredWorld(){
        return "Hello Unsecured World!";
    }

    @GetMapping("/secured")
    public String securedWorld(){
        return "Hello Secured World!";
    }
}


