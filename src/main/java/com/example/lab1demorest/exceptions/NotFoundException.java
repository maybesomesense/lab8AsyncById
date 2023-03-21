package com.example.lab1demorest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Index not found")
public class NotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message){
        super(message);
    }
}
