package com.example.lab1demorest.service;

import com.example.lab1demorest.entity.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciServiceTest {
    private FibonacciService service;

    @BeforeEach
    void SetUp() throws Exception{
        service = new FibonacciService();
    }

    @Test
    public void testEmptiness(){
        String number = "1001";
        Result fibonacciResult = service.count(new BigInteger(number));
        assertNotNull(fibonacciResult);
        //Assertions.assertTrue(fibonacciResult);
    }

    @Test
    public void testEmpty(){
        String number = "-5";
        Result fibonacciResult = service.count(new BigInteger(number));
        //assertThrows(fibonacciResult, service.count(new BigInteger(number)));
        //Assertions.assertTrue(fibonacciResult);
    }
}