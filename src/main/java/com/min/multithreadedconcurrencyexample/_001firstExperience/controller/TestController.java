package com.min.multithreadedconcurrencyexample._001firstExperience.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: multithreaded-concurrency-example
 * @description:
 * @author: mcy
 * @create: 2018-10-27 18:32
 **/

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
