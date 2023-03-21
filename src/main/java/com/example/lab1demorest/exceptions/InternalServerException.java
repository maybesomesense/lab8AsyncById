package com.example.lab1demorest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error occurred")
public class InternalServerException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    public InternalServerException(String message){
        super(message);
    }
}
