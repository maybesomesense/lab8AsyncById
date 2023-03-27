package com.example.lab1demorest.validator;

import com.example.lab1demorest.controller.ExController;
import com.example.lab1demorest.entity.ValidationNumbersError;
//import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExampleValidator {
    private static final Logger logger = LoggerFactory.getLogger(ExController.class);

    public ValidationNumbersError validateParameter(String number){
        ValidationNumbersError response = new ValidationNumbersError();


        if(NumberValidator.isEmpty(number)){
            logger.error("number is empty");
            response.addErrorMessages("number is empty");
        } else if(!NumberValidator.isNumeric(number)){
            logger.error("number cannot be a word or contain a letter in the number");
            response.addErrorMessages("number cannot be a word or contain a letter in the number");
        } else if(NumberValidator.isLong(number)){
            logger.error("the number can't be that big. it can be no more than 10000");
            response.addErrorMessages("the number can't be that big. it can be no more than 10000");
        } else if(NumberValidator.isNegative(number)){
            logger.error("Fibonacci number cannot be negative");
            response.addErrorMessages("Fibonacci number cannot be negative");
        }

        return response;
    }


}
