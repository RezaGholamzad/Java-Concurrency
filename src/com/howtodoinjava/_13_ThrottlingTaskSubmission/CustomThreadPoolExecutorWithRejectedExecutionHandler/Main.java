package com.howtodoinjava._13_ThrottlingTaskSubmission.CustomThreadPoolExecutorWithRejectedExecutionHandler;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);
        CustomThreadPoolExecutor threadPoolExecutor = new CustomThreadPoolExecutor(10,
                20,5000, TimeUnit.MILLISECONDS, blockingQueue);

        threadPoolExecutor.setRejectedExecutionHandler((runnable, executor) -> {
            System.out.println("Task Rejected : " + ((Task) runnable).getName());
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Lets add another time : " + ((Task) runnable).getName());
            executor.execute(runnable);
        });
        // Let start all core threads initially
        threadPoolExecutor.prestartAllCoreThreads();

        do {
            threadCounter++;
            // Adding threads one by one
            System.out.println("Adding Task : " + threadCounter);
            threadPoolExecutor.execute(new Task(Integer.toString(threadCounter)));
        } while (threadCounter != 100);

    }
}
