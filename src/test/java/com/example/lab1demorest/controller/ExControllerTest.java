package com.example.lab1demorest.controller;

import com.example.lab1demorest.database.RepositoryService;
import com.example.lab1demorest.entity.Counters;
import com.example.lab1demorest.entity.ResponsesSize;
import com.example.lab1demorest.entity.Result;
import com.example.lab1demorest.entity.ValidationNumbersError;
import com.example.lab1demorest.exceptions.NotFoundException;
import com.example.lab1demorest.memory.InMemoryStorage;
import com.example.lab1demorest.service.AggregateService;
import com.example.lab1demorest.service.CounterService;
import com.example.lab1demorest.service.FibonacciService;
import com.example.lab1demorest.validator.ExampleValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExControllerTest {
    @Mock
    private FibonacciService fibonacciService;
    @Mock
    private ExampleValidator exampleValidator;
    @Mock
    private InMemoryStorage inMemoryStorage;
    @Mock
    private CounterService counterService;
    @Mock
    private AggregateService aggregateService;
    @Mock
    private RepositoryService repositoryService;

    @InjectMocks
    private ExController exController = new ExController(fibonacciService, exampleValidator,
                                                        inMemoryStorage, counterService, aggregateService,
                                                        repositoryService);

    @Test
    public void defaultTest(){
        String number = "5";
        BigInteger answer = BigInteger.valueOf(5);
        ValidationNumbersError validator = new ValidationNumbersError();
        when(fibonacciService.count(new BigInteger(number))).thenReturn(new Result((BigInteger.valueOf(5)), BigInteger.valueOf(5)));
        when(exampleValidator.validateParameter(number)).thenReturn(validator);
        ResponseEntity<Object> response = exController.fibonacci(number);
        Result result = (Result) response.getBody();
        assertNotNull(response);
        assertEquals(answer, Objects.requireNonNull(result).getResult());
    }

    @Test
    public void notFound(){
        String number = "4";
        String answer = "Internal server Error occurred";
        ValidationNumbersError validator = new ValidationNumbersError();
        when(exampleValidator.validateParameter(number)).thenReturn(validator);
        when(fibonacciService.count(new BigInteger(number))).thenThrow(NotFoundException.class);

        ResponseEntity<Object> response = exController.fibonacci(number);
        ValidationNumbersError result = (ValidationNumbersError) response.getBody();
        assertNotNull(result);
        assertThrows(NotFoundException.class, () -> fibonacciService.count(new BigInteger(number)));
        assertEquals(answer, Objects.requireNonNull(result).getErrorMessages().get(0));
    }

    @Test
    public void negativeNumber(){
        String number = "-5";
        String answer = "Fibonacci number cannot be negative";
        ValidationNumbersError validator = new ValidationNumbersError();
        validator.addErrorMessages("Fibonacci number cannot be negative");
        when(exampleValidator.validateParameter(number)).thenReturn(validator);

        ResponseEntity<Object> response = exController.fibonacci(number);
        ValidationNumbersError result = (ValidationNumbersError) response.getBody();
        assertNotNull(result);
        assertEquals(validator, result);
    }

    @Test
    public void testGetAllSavedResults(){
        when(inMemoryStorage.getAllSavedResults()).thenReturn(null);

        ResponseEntity<Object> response = exController.getAllFibonacciAnswers();
        Object result = response.getBody();
        assertNull(result);

    }

    @Test
    public void testGelAllSavedResultsSize(){
        when(inMemoryStorage.size()).thenReturn(0);

        ResponseEntity<Object> response = exController.getAllFibonacciAnswersSize();
        ResponsesSize result = (ResponsesSize) response.getBody();
        assertEquals(new ResponsesSize(0), result);
    }

    @Test
    public void testCounters(){
        when(counterService.getSync()).thenReturn(1);
        when(counterService.getUnsync()).thenReturn(1);

        ResponseEntity<Object> responseEntity = exController.getCounters();

        Counters counters = (Counters) responseEntity.getBody();

        assertEquals(counters, new Counters(1,1));
    }
}
