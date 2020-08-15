package com.howtodoinjava._14_excutorFramework._1_excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CheckTask {

    /*
        we have two tasks running. Neither is expected to terminate, and both should run for
        the duration of the application’s life.
    */
    static ExecutorService executorService = Executors.newFixedThreadPool(2);;
    static volatile Future taskOneResult = null;
    static volatile Future taskTwoResult = null;

    /*
        If any task throws an exception, the application will catch it and restart the task.
        If any task runs to completion, the application will notice and restart the task.
     */
    static void check(){
        if (taskOneResult == null
                || taskOneResult.isDone()
                || taskOneResult.isCancelled()){
            taskOneResult = executorService.submit(new TaskOne());
        }

        if (taskTwoResult == null
                || taskTwoResult.isCancelled()
                || taskTwoResult.isDone()){
            taskTwoResult = executorService.submit(new TaskTwo());
        }
    }
}
