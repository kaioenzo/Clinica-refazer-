package com.kaio.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HomeController {

    @GetMapping
    public String helloWorld(){
        return "<h1>hello world</h1><span>aaa22222</span>";
    }
}
