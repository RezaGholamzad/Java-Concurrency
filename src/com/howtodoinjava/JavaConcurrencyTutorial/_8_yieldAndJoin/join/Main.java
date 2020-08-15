package com.howtodoinjava.JavaConcurrencyTutorial._8_yieldAndJoin.join;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("First task started");
                System.out.println("Sleeping for 2 seconds");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("First task completed");
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Second task completed");
            }
        });

        thread.start();
        /*
            The join() method of a Thread instance can be used to “join” the start of a thread’s
            execution to the end of another thread’s execution so that a thread will not start
            running until another thread has ended. If join() is called on a Thread instance,
            the currently running thread will block until the Thread instance has finished executing.

            Giving a timeout within join(), will make the join() effect to be nullified after
            the specific timeout. When the timeout is reached, the main thread and taskThread
            are equally probable candidates to execute. However, as with sleep, join is
            dependent on the OS for timing, so you should not assume that join will wait
            exactly as long as you specify.

            Like sleep, join responds to an interrupt by exiting with an InterruptedException.
         */
        thread.join();
        thread1.start();
    }
}
