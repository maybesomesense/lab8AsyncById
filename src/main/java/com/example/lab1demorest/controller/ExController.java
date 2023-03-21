package com.example.lab1demorest.controller;

import com.example.lab1demorest.annotations.CustomExceptionHandler;
import com.example.lab1demorest.entity.ValidationNumbersError;
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
    private static final Logger logger = LoggerFactory.getLogger(ExController.class);
    private final FibonacciService fibonacciService;
    private final ExampleValidator validator;

    @Autowired
    public ExController(final FibonacciService service, ExampleValidator validator) {
        this.fibonacciService = service;
        this.validator = validator;
    }
    @GetMapping( "/fibonacci")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> fibonacci(@RequestParam(required = true, name = "number") String number){
        ValidationNumbersError response = validator.validateParameter(number);

        if(response.getErrorMessages().size() != 0){
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            logger.error("Number argument isn't valid");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            if(!number.isEmpty()){
                return ResponseEntity.ok(fibonacciService.count(new BigInteger(number)));
            } else{
                // check here
                response.addErrorMessages("Number " + number + " not found");
                response.setStatus(HttpStatus.NOT_FOUND.name());
                logger.error("Number " + number + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch(Exception error){
            response.addErrorMessages("Internal server Error occurred");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            logger.error("Internal server Error occurred");
            return new ResponseEntity<>(response, HttpStatus.INSUFFICIENT_STORAGE);
        }
    }
}
