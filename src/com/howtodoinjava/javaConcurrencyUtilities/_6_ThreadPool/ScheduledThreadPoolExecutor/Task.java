package com.howtodoinjava.javaConcurrencyUtilities._6_ThreadPool.ScheduledThreadPoolExecutor;

import java.util.Date;

public class Task implements Runnable{

    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println("Executing : " + name + ", Current Seconds : " + new Date().getSeconds());
    }
}
