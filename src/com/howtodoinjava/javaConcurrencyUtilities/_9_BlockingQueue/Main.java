package com.howtodoinjava.javaConcurrencyUtilities._9_BlockingQueue;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Integer threadCounter = 0;

        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);

        CustomThreadPoolExecutor customThreadPoolExecutor =
                new CustomThreadPoolExecutor(10,20,
                5000, TimeUnit.MILLISECONDS, blockingQueue);

        // set reject execution handler
        customThreadPoolExecutor.setRejectedExecutionHandler((runnable, executor) -> {

            System.out.println("DemoTask Rejected : "
                    + ((Task) runnable).getName());

            System.out.println("Waiting for a second !!");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Lets add another time : "
                    + ((Task) runnable).getName());

            executor.execute(runnable);
        });

        // Let start all core threads initially
        customThreadPoolExecutor.prestartAllCoreThreads();

        while (true){
            threadCounter++;
            // Adding threads one by one
            System.out.println("Adding DemoTask : " + threadCounter);
            customThreadPoolExecutor.execute(new Task(threadCounter.toString()));
            if (threadCounter == 100){
                break;
            }
        }
    }
}
