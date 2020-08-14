package com.howtodoinjava._11_CallableAndFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

//        using two threads and 4 numbers.
        ThreadPoolExecutor executor = (ThreadPoolExecutor)
                Executors.newFixedThreadPool(2);

        Random random = new Random();

        List<Future<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Integer number = random.nextInt(10);
            FactorialCalculator factorialCalculator = new FactorialCalculator(number);

            /*
                Here we sent a Callable object to be executed in an executor
                using the submit() method. This method receives a Callable object
                as a parameter and returns a Future object
             */
            Future<Integer> result = executor.submit(factorialCalculator);
            resultList.add(result);
        }

        for (Future<Integer> future : resultList){

            /*
                We can control the status of the task – we can cancel the task and
                check if it has finished. For this purpose, we have used the isDone()
                method to check if the tasks had finished.

                We can get the result returned by the call() method. For this purpose,
                we have used the get() method. This method waits until the Callable
                object has finished the execution of the call() method and
                has returned its result.
             */
            try {

                System.out.println("future is result : " + future.get() +
                        " and task done is : " + future.isDone());

                /*
                    The Future interface provides another version of the get() method i.e.
                    get(timeout,TimeUnit). This version of the get method,
                    if the result of the task isn’t available, waits for it for the specified time.
                    If the specified period of time passes and the result isn’t yet available,
                    the method returns a TimeoutException.
                 */
//                System.out.println("future is result : " + future.get(20, TimeUnit.MILLISECONDS) +
//                        " and task done is : " + future.isDone());

            } catch (InterruptedException | ExecutionException e) {
                /*
                    If the thread is interrupted while the get() method is
                    waiting for the result, it throws an InterruptedException exception.
                    If the call() method throws an exception, this method throws an
                    ExecutionException exception.
                 */
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
