package com.howtodoinjava.JavaConcurrencyTutorial._13_ThrottlingTaskSubmission.ThreadPoolExecutorAndSemaphore;

import java.util.concurrent.*;

//Our new throttling enabled BlockingThreadPoolExecutor
public class BlockingThreadPoolExecutor extends ThreadPoolExecutor {
    private final Semaphore semaphore;

    public BlockingThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                      long keepAliveTime, TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        /*
            You can control the number of tasks executing at any time parameter passes
            in Semaphore constructor.
         */
        this.semaphore = new Semaphore(corePoolSize + 50);
    }

    @Override
    public void execute(Runnable command) {
        boolean acquired = false;

        do {
            try {
                semaphore.acquire();
                acquired = true;
            } catch (InterruptedException e) {
                //LOGGER.warn("InterruptedException whilst aquiring semaphore", e);
                e.printStackTrace();
            }
        }while (!acquired);

        try {
            super.execute(command);
        }catch (final RejectedExecutionException e){
            System.out.println("Task Rejected");
            semaphore.release();
            throw e;
        }
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null)
        {
            t.printStackTrace();
        }
        semaphore.release();
    }
}
