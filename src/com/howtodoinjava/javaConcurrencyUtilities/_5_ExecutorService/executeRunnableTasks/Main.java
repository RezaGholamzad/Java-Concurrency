package com.howtodoinjava.javaConcurrencyUtilities._5_ExecutorService.executeRunnableTasks;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        // task
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("Current time :: " + LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        /*
            Execute Runnable tasks :

            void execute(Runnable task) – executes the given command at some time in the future.

            Future submit(Runnable task) – submits a runnable task for execution and returns a
            Future representing that task. The Future’s get() method will return null
            upon successful completion.

            Future submit(Runnable task, T result) – Submits a runnable task for execution and
            returns a Future representing that task. The Future’s get() method will return
            the given result upon successful completion.
         */

        //Executor service instance
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //1. execute task using execute() method
        executorService.execute(runnableTask);

        //2. execute task using submit() method
        Future<String> result = executorService.submit(runnableTask, "done");

        while (!result.isDone()){
            try {
                System.out.println("the method return value : " + result.get());
                break;
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }

            //Sleep for 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Shut down the executor service
        executorService.shutdown();

    }
}
