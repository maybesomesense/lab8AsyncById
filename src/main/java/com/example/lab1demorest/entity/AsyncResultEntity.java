package com.example.lab1demorest.entity;

public class AsyncResultEntity {
    private String message;
    private int id;


    public AsyncResultEntity(int id) {
        this.message = "You can get element by id: ";
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}
