package com.example.lab1demorest.entity;

import java.util.ArrayList;
import java.util.List;

public class ValidationNumbersError {
    private final List<String> errorMessages = new ArrayList<>();
    private String status;

    public List<String> getErrorMessages(){
        return errorMessages;
    }

    public void addErrorMessages(String errorMessage){
        this.errorMessages.add(errorMessage);
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
