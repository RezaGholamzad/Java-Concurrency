package com.howtodoinjava.javaConcurrencyUtilities._6_ThreadPool.ScheduledThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //ScheduledThreadPoolExecutor class is a child class of the ThreadPoolExecutor class
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors
                .newScheduledThreadPool(2);

        Task task = new Task("repeat task");
        System.out.println("created : " + task.getName());

        /*
            To execute a task in this scheduled executor after a period of time,
            you have to use the schedule() method. This method receives
            the following three parameters:

            +The task you want to execute
            +The period of time you want the task to wait before its execution
            +The unit of the period of time, specified as a constant of the TimeUnit class
         */
//        executor.schedule(task, 5 , TimeUnit.SECONDS);

        /*
            we have used the scheduledAtFixedRate() method.
            This method accepts four parameters:

            +the task you want to execute periodically,
            +the delay of time until the first execution of the task,
            +the period between two executions,
            +and the time unit of the second and third parameters.
         */
        executor.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);

        /*
            Finally, you can configure the behavior of the ScheduledThreadPoolExecutor class
            when you call the shutdown() method and there are pending tasks waiting for
            the end of their delay time. The default behavior is that those tasks will
            be executed despite the finalization of the executor. You can change this
            behavior using the setExecuteExistingDelayedTasksAfterShutdownPolicy()
            method of the ScheduledThreadPoolExecutor class. With false, at the time
            of shutdown(), pending tasks won’t get executed.
         */
        executor.shutdown();
    }
}
