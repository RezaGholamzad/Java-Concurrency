package com.howtodoinjava.JavaConcurrencyTutorial._4_synchronizedKeyword;

public class Math {

    void printNumbers(int n) throws InterruptedException {

        /*
            When a thread wants to execute synchronized statements inside
            the synchronized block, it MUST acquire the lock on lockObject‘s(this) monitor.
            At a time, only one thread can acquire the monitor of a lock object.
            So all other threads must wait till this thread, currently acquired the lock,
            finish it’s execution.

            Keep in mind that if a thread is put on sleep (using sleep() method)
            then it does not release the lock. At this sleeping time, no thread
            will be executing the synchronized block statements.

            Java synchronization will throw NullPointerException if lock object
            used in 'synchronized (lock)' is null.

            Java synchronized keyword is re-entrant in nature it means if
            a synchronized method calls another synchronized method which requires
            same lock then current thread which is holding lock can enter into that
            method without acquiring lock.
         */
        synchronized (this){

            for (int i = 0; i < n; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
                Thread.sleep(500);
            }
        }
    }

    /*
        Similar to synchronized block, a thread MUST acquire the lock on the associated monitor
        object with synchronized method. In case of synchronized method, the lock object is:

        ‘.class’ object – if the method is static.

        ‘this’ object – if the method is not static. ‘this’ refer to reference to current object
        in which synchronized method is invoked.
     */
    synchronized void printNumbers2(int n ) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            System.out.println(Thread.currentThread().getName() + "::" + i);
            Thread.sleep(500);
        }
    }
}
