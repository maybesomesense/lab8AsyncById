package com.example.lab1demorest.entity;

import java.util.Objects;

public class Counters {
    private int sync;
    private int unsync;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counters counters = (Counters) o;
        return sync == counters.sync && unsync == counters.unsync;
    }

    public Counters(int sync, int unsync){
        this.sync = sync;
        this.unsync = unsync;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public int getUnsync() {
        return unsync;
    }

    public void setUnsync(int unsync) {
        this.unsync = unsync;
    }
}
