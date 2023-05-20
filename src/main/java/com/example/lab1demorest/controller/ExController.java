package com.example.lab1demorest.controller;

import com.example.lab1demorest.annotations.CustomExceptionHandler;
import com.example.lab1demorest.database.EntityDB;
import com.example.lab1demorest.database.RepositoryService;
import com.example.lab1demorest.entity.*;
import com.example.lab1demorest.exceptions.ErrorMessage;
import com.example.lab1demorest.memory.InMemoryStorage;
import com.example.lab1demorest.service.AggregateService;
import com.example.lab1demorest.service.CounterService;
import com.example.lab1demorest.service.FibonacciService;
import com.example.lab1demorest.validator.ExampleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/lab")
@CustomExceptionHandler
public class ExController {
    private static Logger logger = LoggerFactory.getLogger(ExController.class);
    private FibonacciService fibonacciService;
    private ExampleValidator validator;
    private InMemoryStorage inMemoryStorage;
    private CounterService counterService;
    private AggregateService aggregateService;
    private RepositoryService repositoryService;

    @Autowired
    public ExController(FibonacciService service, ExampleValidator validator, InMemoryStorage inMemoryStorage,
                        CounterService counterService, AggregateService aggregateService,
                        RepositoryService repositoryService) {
        this.fibonacciService = service;
        this.validator = validator;
        this.inMemoryStorage = inMemoryStorage;
        this.counterService = counterService;
        this.aggregateService = aggregateService;
        this.repositoryService = repositoryService;
    }

    @GetMapping("/responses")
    public ResponseEntity<Object> getAllFibonacciAnswers() {
        return new ResponseEntity<>(inMemoryStorage.getAllSavedResults(), HttpStatus.OK);
    }

    @GetMapping("/response")
    public ResponseEntity<Object> getById(int index) {
        return new ResponseEntity<>(inMemoryStorage.getSavedResult(BigInteger.valueOf(index)), HttpStatus.OK);
    }

    @GetMapping("/responses/size")
    public ResponseEntity<Object> getAllFibonacciAnswersSize() {
        return new ResponseEntity<>(new ResponsesSize(inMemoryStorage.size()), HttpStatus.OK);
    }

    @GetMapping("/getcounters")
    public ResponseEntity<Object> getCounters() {
        return ResponseEntity.ok(new Counters(counterService.getSync(), counterService.getUnsync()));
    }

    @GetMapping("/fibonacci")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> fibonacci(@RequestParam(required = true, name = "number") String number) {
        logger.info("1. validation");
        ValidationNumbersError response = validator.validateParameter(number);

        if (response.getErrorMessages().size() != 0) {
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            logger.error("Number argument isn't valid");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Result answer;
        try {
            logger.info("2. count result");

            counterService.incrementUnsync();
            counterService.incrementSync();

            BigInteger index = new BigInteger(number);

            if (repositoryService.containsInDatabase(index)) {
                answer = repositoryService.getByIndex(index);
            } else {
                answer = fibonacciService.count(new BigInteger(number));
                repositoryService.save(answer);
            }

        } catch (Exception error) {
            response.addErrorMessages("Internal server Error occurred");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            logger.error("Internal server Error occurred");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        inMemoryStorage.saveResult(answer);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping("/fibonaccibulk")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BulkResult> fibonacciBulk(@RequestBody List<BulkParameter> numbers) {
        ErrorMessage errors = new ErrorMessage();
        Map<BigInteger, Result> resultMap = new HashMap<>();
        numbers.forEach(p -> {
            String string = p.toString();
            ValidationNumbersError tempErrors = validator.validateParameter(string);
            if (!tempErrors.isEmpty()) {
                tempErrors.getErrorMessages().forEach(errors::addError);
            } else {
                BigInteger index = new BigInteger(string);
                Result answer;
                if (repositoryService.containsInDatabase(index)) {
                    answer = repositoryService.getByIndex(index);
                } else {
                    answer = fibonacciService.count(new BigInteger(string));
                    repositoryService.save(answer);
                }
                resultMap.put(index, answer);
            }
        });
        resultMap.entrySet().stream().forEach(e -> {
            inMemoryStorage.saveResult(e.getValue());
        });
        BigInteger min = aggregateService.getMinNumber(resultMap);
        BigInteger max = aggregateService.getMaxNumber(resultMap);
        BigInteger middle = aggregateService.getMiddleNumber(resultMap);

        BulkResult result = new BulkResult(min, max, middle, resultMap);
        return ResponseEntity.ok(result);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllData")
    public ResponseEntity<List<Result>> getAllData() {
        return ResponseEntity.ok(repositoryService.getAllResults());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/fibonacciAsync")
    public ResponseEntity<Object> fibonacciAsync(@RequestParam(required = true, name = "number") String number) {
        logger.info("1. validation");
        ValidationNumbersError response = validator.validateParameter(number);

        if (response.getErrorMessages().size() != 0) {
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            logger.error("Number argument isn't valid");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            logger.info("2. count result");

            counterService.incrementUnsync();
            counterService.incrementSync();

            BigInteger index = new BigInteger(number);

            if (repositoryService.containsInDatabase(index)) {
                return ResponseEntity.ok(new AsyncResultEntity(Integer.parseInt(number)));
            }
            int id = Integer.parseInt(number);
            CompletableFuture.runAsync(() -> {
                Result answer = fibonacciService.count(new BigInteger(number));
                repositoryService.save(answer);
                inMemoryStorage.saveResult(answer);
            });
            return ResponseEntity.ok(new AsyncResultEntity(id));

        } catch (Exception error) {
            response.addErrorMessages("Internal server Error occurred");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            logger.error("Internal server Error occurred");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getByIdAsync")
    public ResponseEntity<EntityDB> getByIdAsync(int id){
        return ResponseEntity.ok(repositoryService.getById(id));
    }
}
