package com.howtodoinjava._13_ThrottlingTaskSubmission.ThreadPoolExecutorAndSemaphore;

import com.howtodoinjava._13_ThrottlingTaskSubmission.CustomThreadPoolExecutorWithRejectedExecutionHandler.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);
        BlockingThreadPoolExecutor threadPoolExecutor = new BlockingThreadPoolExecutor(10,
                20,5000, TimeUnit.MILLISECONDS, blockingQueue);

        threadPoolExecutor.setRejectedExecutionHandler((runnable, executor) -> {
            System.out.println("DemoTask Rejected : " + ((Task) runnable).getName());
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
            System.out.println("Adding Task : " + threadCounter);

            /*
                When you run the DemoExecutor program using BlockingThreadPoolExecutor
                in place of CustomThreadPoolExecutor, you will not see any task rejected
                and all tasks will be executed successfully.
             */
            threadPoolExecutor.execute(new Task(Integer.toString(threadCounter)));
        }while (threadCounter != 100);
    }
}
