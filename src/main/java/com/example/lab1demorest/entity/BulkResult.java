package com.example.lab1demorest.entity;

import java.math.BigInteger;
import java.util.Map;

public class BulkResult {
    private BigInteger min;
    private BigInteger max;
    private BigInteger middle;
    private Map<BigInteger, Result> resultMap;

    public BigInteger getMin() {
        return min;
    }

    public BigInteger getMax() {
        return max;
    }

    public BigInteger getMiddle() {
        return middle;
    }

    public BulkResult(BigInteger min, BigInteger max, BigInteger middle, Map<BigInteger, Result> resultMap) {
        this.min = min;
        this.max = max;
        this.middle = middle;
        this.resultMap = resultMap;
    }


    public Map<BigInteger, Result> getResultMap() {
        return resultMap;
    }
}
