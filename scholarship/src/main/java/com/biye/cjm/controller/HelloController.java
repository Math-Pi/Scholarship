package com.biye.cjm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenjiaming
 * @Date 2021/2/23
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
