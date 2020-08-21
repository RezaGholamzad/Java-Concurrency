package com.howtodoinjava.javaConcurrencyUtilities._6_ThreadPool.ThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
                .newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            Task task = new Task("task " + i);
            System.out.println("created : " + task.getName());

            executor.execute(task);
        }
        executor.shutdown();
    }
}
