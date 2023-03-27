package com.example.lab1demorest.memory;

import com.example.lab1demorest.entity.Result;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;

@Component
public class InMemoryStorage
{
    private Map<BigInteger, Result> dataStorage = new HashMap<BigInteger, Result>();

    public void saveResult(Result response)
    {
        dataStorage.put(response.getResult(), response);
    }

    public Result getSavedResult(BigInteger id)
    {
        return dataStorage.get(id);
    }

    public Integer size()
    {
        return dataStorage.size();
    }   

    public Collection<Result> getAllSavedResults()
    {
        return dataStorage.values();
    }
}