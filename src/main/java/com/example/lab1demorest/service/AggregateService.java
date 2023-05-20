package com.example.lab1demorest.service;

import com.example.lab1demorest.entity.Result;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

@Service
public class AggregateService {
    public BigInteger getMinNumber(Map<BigInteger, Result> mapResult){
        BigInteger min = mapResult.entrySet().stream().min(
                (a,b) -> a.getValue().getResult().compareTo(b.getValue().getResult())
        ).get().getValue().getResult();
        return min;
    }

    public BigInteger getMaxNumber(Map<BigInteger, Result> mapResult){
        BigInteger max = mapResult.entrySet().stream().max(
                (a,b) -> a.getValue().getResult().compareTo(b.getValue().getResult())
        ).get().getValue().getResult();
        return max;
    }

    public BigInteger getMiddleNumber(Map<BigInteger, Result> mapResult){
        BigInteger middle = BigInteger.valueOf(0);
        for (Map.Entry<BigInteger,Result> entry: mapResult.entrySet()){
            middle = middle.add(entry.getValue().getResult());
        }
        return middle.divide(BigInteger.valueOf(mapResult.entrySet().size()));
    }
}
