package com.example.lab1demorest.controller;

import com.example.lab1demorest.entity.Result;
import com.example.lab1demorest.service.FibonacciCount;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("api/lab")
public class ExController {
    private final FibonacciCount service;
    public ExController(FibonacciCount service) {
        this.service = service;
    }
    @GetMapping( "/fibonacci")
    public Result fibonacci(@RequestParam BigInteger param1){return service.count(param1);}
}
