package com.example.lab1demorest.service;

import com.example.lab1demorest.entity.Result;
import com.example.lab1demorest.entity.ValidationNumbersError;
import com.example.lab1demorest.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FibonacciService {
    // рекурсивный вариант уходит в overflow где-то на 40 индексе, поэтому идеальной реализацией будет через цикл
    public Result count(BigInteger index){
       if(index.compareTo(BigInteger.valueOf(4)) == 0)
            throw new NotFoundException("number " + index + " not found");
//       if(index.compareTo(BigInteger.valueOf(44)) == 0)
//           return new Result(BigInteger.valueOf(44),BigInteger.valueOf(0));

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