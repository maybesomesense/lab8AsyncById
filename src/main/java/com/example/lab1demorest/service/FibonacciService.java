package com.example.lab1demorest.service;

import com.example.lab1demorest.entity.Result;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FibonacciService {
    // рекурсивный вариант уходит в overflow где-то на 40 индексе, поэтому идеальной реализацией будет через цикл
    public Result count(BigInteger index){
        // пример INTERNAL_SERVER_ERROR
        if(index == null){
            throw new NullPointerException("throws NullPointerException");
        }
        else if(index.compareTo(BigInteger.valueOf(0)) < 0){
            throw new NullPointerException("throws NullPointerException");
        }
        else if(index.compareTo(BigInteger.valueOf(1000)) > 0){
            throw new NullPointerException("throws NullPointerException");
        }

        BigInteger OldValue = BigInteger.valueOf(0);
        BigInteger Value = BigInteger.valueOf(1);
        BigInteger Hold;
        if(index.compareTo(BigInteger.valueOf(1)) < 0){return new Result(BigInteger.valueOf(0), index);}
        for(BigInteger n = BigInteger.valueOf(1); n.compareTo(index) < 0; n = n.add(BigInteger.valueOf(1)))
        {
            Hold = Value;
            Value = Value.add(OldValue);
            OldValue = Hold;
        }
        return new Result(Value, index);
    }
}