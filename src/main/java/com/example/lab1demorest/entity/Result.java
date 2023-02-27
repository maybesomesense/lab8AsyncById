package com.example.lab1demorest.entity;

import java.math.BigInteger;

public class Result {
    BigInteger result;
    BigInteger index;

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
}
