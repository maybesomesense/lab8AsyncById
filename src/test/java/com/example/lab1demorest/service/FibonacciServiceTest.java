package com.example.lab1demorest.service;

import com.example.lab1demorest.entity.Result;
import com.example.lab1demorest.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciServiceTest {
    private FibonacciService service = new FibonacciService();

    @Test
    public void longNumber(){
        String number = "4";
        Throwable thrown = assertThrows(NotFoundException.class,
                () -> service.count(new BigInteger(number)));
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testFibonacci(){
        String number = "5";
        Result result = new Result(BigInteger.valueOf(5),BigInteger.valueOf(5));
        Result answer = service.count(new BigInteger(number));
        assertNotNull(answer);
        assertEquals(result, answer);
    }

    @Test
    public void test44(){
        String number = "44";
        Result result = new Result(BigInteger.valueOf(44),BigInteger.valueOf(0));
        Result answer = service.count(new BigInteger(number));
        assertNotNull(answer);
        assertEquals(result, answer);
    }
}