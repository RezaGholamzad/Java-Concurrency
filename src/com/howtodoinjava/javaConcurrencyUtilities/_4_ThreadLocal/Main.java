package com.howtodoinjava.javaConcurrencyUtilities._4_ThreadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(task, ""+i).start();
        }
    }
}
