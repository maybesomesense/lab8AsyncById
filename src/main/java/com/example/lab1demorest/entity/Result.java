package com.example.lab1demorest.entity;

import com.example.lab1demorest.service.FibonacciService;

import java.math.BigInteger;

public class Result {
    private BigInteger result;
    private BigInteger index;

    public Result(BigInteger result, BigInteger index){
        this.index = index;
        this.result = result;
    }
    public BigInteger getResult(){
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }

    public BigInteger getIndex() {
        return index;
    }

    public void setIndex(BigInteger index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        Result object = (Result) o;

        if(!result.equals(object.getResult())){
            return false;
        }
        return index.equals(object.getIndex());
    }
}
