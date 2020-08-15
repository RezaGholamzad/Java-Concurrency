package com.howtodoinjava._14_excutorFramework._2_excutor_multiRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<Runnable> worksQueue = new ArrayBlockingQueue<>(10);

        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandlerImpl();

        MultiRunnable.executor = new ThreadPoolExecutor(2,
                3, 10, TimeUnit.SECONDS,
                worksQueue, rejectedHandler);

        MultiRunnable.executor.prestartAllCoreThreads();

        List<Runnable> taskGroup = new ArrayList<>();
        taskGroup.add(new TaskOne());
        taskGroup.add(new TaskTwo());
        taskGroup.add(new TaskThree());

        worksQueue.add(new MultiRunnable(taskGroup));
    }
}