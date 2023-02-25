package com.example.lab1demorest.controller;

import com.example.lab1demorest.Entity.result;
import com.example.lab1demorest.service.fibonacciCount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("api/lab")
public class ExController {
    private final fibonacciCount service;
    public ExController(fibonacciCount service) {
        this.service = service;
    }
    @GetMapping("/fibonacci")
    public result fibonacci(@RequestParam BigInteger param1){
        return service.count(param1);
    }


}
