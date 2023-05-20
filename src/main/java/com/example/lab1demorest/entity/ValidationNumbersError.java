package com.example.lab1demorest.entity;

import com.example.lab1demorest.validator.ExampleValidator;

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

    public boolean isEmpty(){
        return errorMessages.isEmpty();
    }
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        ValidationNumbersError object = (ValidationNumbersError) o;

        if(!status.equals(object.getStatus())){
            return false;
        }
        return errorMessages.get(0).equals(object.getErrorMessages().get(0));
    }
}
