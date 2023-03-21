package com.example.lab1demorest.exceptions;

import java.util.ArrayList;

public class ErrorMessage {
    String status;
    ArrayList<String> listOfErrors = new ArrayList<>();

    ErrorMessage(String error){
        listOfErrors.add(error);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getListOfErrors() {
        return listOfErrors;
    }

    public void setListOfErrors(ArrayList<String> listOfErrors) {
        this.listOfErrors = listOfErrors;
    }
}
