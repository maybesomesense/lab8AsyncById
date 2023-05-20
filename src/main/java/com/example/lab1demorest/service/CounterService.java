package com.example.lab1demorest.service;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private Integer sync = 0;
    private Integer unsync = 0;

    public synchronized void incrementSync(){
        sync++;
    }

    public void incrementUnsync(){
        unsync++;
    }

    public int getSync() {
        return sync;
    }

    public int getUnsync() {
        return unsync;
    }
}
