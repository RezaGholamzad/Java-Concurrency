package com.howtodoinjava.javaConcurrencyUtilities._9_BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {
    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                    long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /*
        ThreadPoolExecutor provides two excellent methods which I will highly recommend
        to override i.e. beforeExecute() and afterExecute() methods. They provide a
        very good handle on the execution lifecycle of runnable to be executed.
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(Thread.currentThread() + " Perform beforeExecute() logic");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            System.out.println(Thread.currentThread() + " Perform exception handler logic");
        }
        System.out.println(Thread.currentThread() + " Perform afterExecute() logic");
    }
}
