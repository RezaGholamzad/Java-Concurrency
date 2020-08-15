package com.howtodoinjava._14_excutorFramework._2_excutor_multiRunnable;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/*
    It’s not necessary that each Runnable should be executed in a separate thread.
    Sometimes, we need to do multiple jobs in a single thread and each job is instance
    of Runnable. To design this type of solution, a multi runnable should be used.
    This multi runnable is nothing but a collection of runnables which needs to be
    executed. Only addition is that this multi runnable is also a Runnable itself.
 */
public class MultiRunnable implements Runnable{

    private final List<Runnable> runnables;
    static ThreadPoolExecutor executor;

    public MultiRunnable(List<Runnable> runnables) {
        this.runnables = runnables;
    }

    @Override
    public void run() {
        for (Runnable runnable : runnables){
//            new Thread(runnable).start();
            executor.submit(runnable);
        }
    }
}
