package com.example.lab1demorest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BulkParameter {
    private String number;

    @JsonCreator
    public BulkParameter(@JsonProperty("number") String number){
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString(){
        return number;
    }
}
