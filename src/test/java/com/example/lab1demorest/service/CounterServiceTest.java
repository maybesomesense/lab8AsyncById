package com.example.lab1demorest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CounterServiceTest {
    private CounterService counterService = new CounterService();

    @Test
    public void counterTest(){
        counterService.incrementSync();
        counterService.incrementUnsync();

        Assertions.assertEquals(counterService.getSync(), 1);
        Assertions.assertEquals(counterService.getUnsync(), 1);
    }
}
