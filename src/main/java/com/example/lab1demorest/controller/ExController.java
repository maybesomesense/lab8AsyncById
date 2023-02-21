package com.example.lab1demorest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("api/lab")
public class ExController {
    @GetMapping("/fibonacci")
    public String fibonacci(@RequestParam BigInteger param1) throws Exception{
        return "The value at index " + param1 + " has the value " + fibonacciCount.count(param1);
    }

    static class fibonacciCount{
        // рекурсивный вариант уходит в overflow где-то на 40 индексе, поэтому идеальной реализацией будет через цикл
        static BigInteger count(BigInteger index){
            BigInteger OldValue = BigInteger.valueOf(0);
            BigInteger Value = BigInteger.valueOf(1);
            BigInteger Hold;
            if(index.compareTo(BigInteger.valueOf(1)) < 0){return BigInteger.valueOf(0);}
            for(BigInteger n = BigInteger.valueOf(1); n.compareTo(index) < 0; n = n.add(BigInteger.valueOf(1)))
            {
                Hold = Value;
                Value = Value.add(OldValue);
                OldValue = Hold;
            }
            return Value;
        }
    }
}
