package com.example.lab1demorest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Argument Isn't Valid")
public class ArgumentMismatchException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentMismatchException(String message){
        super(message);
    }
}
