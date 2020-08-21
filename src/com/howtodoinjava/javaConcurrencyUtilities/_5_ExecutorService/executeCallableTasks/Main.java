package com.howtodoinjava.javaConcurrencyUtilities._5_ExecutorService.executeCallableTasks;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        // callable task
        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(5000);
            return "Current time : " + LocalDateTime.now();
        };

        //Executor service instance
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        List<Callable<String>> taskList = Arrays.asList(callableTask, callableTask, callableTask);

        /*
            Execute Callable tasks :

            1) Future submit(callableTask) – submits a value-returning task for execution and
            returns a Future representing the pending results of the task.

            2) List<Future> invokeAll(Collection tasks) – executes the given tasks, returning
            a list of Futures holding their status and results when all complete. Notice
            that result is available only when all tasks are completed.
            Note that a completed task could have terminated either normally
            or by throwing an exception.

            3) List<Future> invokeAll(Collection tasks, timeOut, timeUnit) – executes the given
            tasks, returning a list of Futures holding their status and results when all
            complete or the timeout expires.


         */
        //1. execute tasks list using invokeAll() method
        try {
            List<Future<String>> results = executorService.invokeAll(taskList);

            /*
                Notice that tasks have been completed with a delay of 1 second because
                there is only one task in the thread pool. But when you run the program,
                all first 3 print statements appear at same time because even if the tasks
                are complete, they wait for other tasks to complete in the list.
             */
            for (Future<String> result : results){
                System.out.println(result.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //2. execute individual tasks using submit() method
        Future<String> result = executorService.submit(callableTask);

        while (!result.isDone()){
            try
            {
                System.out.println("The method return value : " + result.get());
                break;
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }

            //Sleep for 1 second
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //shut down the executor service
        executorService.shutdown();
    }
}
