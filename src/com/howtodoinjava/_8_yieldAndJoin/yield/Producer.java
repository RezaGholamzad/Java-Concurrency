package com.howtodoinjava._8_yieldAndJoin.yield;

public class Producer extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i am producer : " + i);
            Thread.yield();
        }
    }
}
