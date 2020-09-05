package com.howtodoinjava.javaConcurrencyUtilities._11_ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class AddTask implements Runnable{

    private final ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

//    One that adds data to a list in large amount
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 1000; i++) {
            list.add(name + ": Element " + i);
        }
    }
}
