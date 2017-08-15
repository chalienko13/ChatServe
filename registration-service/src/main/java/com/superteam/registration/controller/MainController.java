package com.superteam.registration.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class MainController {


    @RequestMapping("/")
    public String main(){
        return "Main message";
    }

    @RequestMapping("/test")
    public String test(){
        return "Hello Man!";
    }
}
