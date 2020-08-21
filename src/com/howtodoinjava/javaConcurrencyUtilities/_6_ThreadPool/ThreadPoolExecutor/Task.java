package com.howtodoinjava.javaConcurrencyUtilities._6_ThreadPool.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

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
        try {
            long duration = (long) (Math.random() * 10);
            System.out.println("Executing : " + name);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
