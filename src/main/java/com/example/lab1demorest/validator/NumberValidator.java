package com.example.lab1demorest.validator;


import java.math.BigInteger;

public class NumberValidator {
    public static boolean isNumeric(String stringNum) {
        if (stringNum == null) {
            return false;
        }
        try {
            BigInteger check = new BigInteger(stringNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String stringNum) {
        return stringNum == null || "".equals(stringNum);
    }

    public static boolean isLong(String stringNum) {
        return new BigInteger(stringNum).compareTo(BigInteger.valueOf(500)) > 0;
    }

    public static boolean isNegative(String stringNum) {
        return new BigInteger(stringNum).compareTo(BigInteger.valueOf(0)) < 0;
    }
}