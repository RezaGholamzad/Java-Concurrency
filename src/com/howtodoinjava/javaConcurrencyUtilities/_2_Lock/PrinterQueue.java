package com.howtodoinjava.javaConcurrencyUtilities._2_Lock;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterQueue {
    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document){
        queueLock.lock();
        try
        {
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() +
                    ": PrintQueue: Printing a Job during " +
                    (duration / 1000) + " seconds :: Time - " + new Date());
            Thread.sleep(duration);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            System.out.printf("%s: The document has been printed\n",
                    Thread.currentThread().getName());
            queueLock.unlock();
            /*
                At the end of the critical section, we have to use the unlock() method
                to free the control of the lock and allow the other threads to run this
                critical section. If you don’t call the unlock() method at the end of
                the critical section, the other threads that are waiting for that block
                will be waiting forever, causing a deadlock situation. If you use try-catch
                blocks in your critical section, don’t forget to put the sentence containing
                the unlock() method inside the finally section.
             */
        }
    }
}
