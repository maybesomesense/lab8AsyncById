package com.example.lab1demorest.controller;

import com.example.lab1demorest.annotations.CustomExceptionHandler;
import com.example.lab1demorest.entity.ResponsesSize;
import com.example.lab1demorest.entity.Result;
import com.example.lab1demorest.entity.ValidationNumbersError;
import com.example.lab1demorest.exceptions.NotFoundException;
import com.example.lab1demorest.memory.InMemoryStorage;
import com.example.lab1demorest.service.FibonacciService;
import com.example.lab1demorest.validator.ExampleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("api/lab")
@CustomExceptionHandler
public class ExController {
    private static Logger logger = LoggerFactory.getLogger(ExController.class);
    private FibonacciService fibonacciService;
    private ExampleValidator validator;
    private InMemoryStorage inMemoryStorage;

    @Autowired
    public ExController(FibonacciService service, ExampleValidator validator, InMemoryStorage inMemoryStorage) {
        this.fibonacciService = service;
        this.validator = validator;
        this.inMemoryStorage = inMemoryStorage;
    }
    @GetMapping( "/fibonacci")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> fibonacci(@RequestParam(required = true, name = "number") String number){
        logger.info("1. validation");
        ValidationNumbersError response = validator.validateParameter(number);

        if(response.getErrorMessages().size() != 0){
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            logger.error("Number argument isn't valid");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Result answer;
        try{
            logger.info("2. count result");
            answer = fibonacciService.count(new BigInteger(number));
        } catch(Exception error){
            response.addErrorMessages("Internal server Error occurred");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            logger.error("Internal server Error occurred");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        inMemoryStorage.saveResult(answer);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @GetMapping("/responses")
    public ResponseEntity<Object> getAllFibonacciAnswers()
    {
        return new ResponseEntity<>(inMemoryStorage.getAllSavedResults(), HttpStatus.OK);
    }

    @GetMapping("/responses/size")
    public ResponseEntity<Object> getAllFibonacciAnswersSize()
    {
        return new ResponseEntity<>(new ResponsesSize(inMemoryStorage.size()), HttpStatus.OK);
    }
}
