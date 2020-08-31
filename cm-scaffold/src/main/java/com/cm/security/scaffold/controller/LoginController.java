package com.cm.security.scaffold.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/7/16 11:12
 */
@RequestMapping("/user")
@RestController
public class LoginController {

    @GetMapping("/login")
    public Object login(){
        return "123";
    }




}
