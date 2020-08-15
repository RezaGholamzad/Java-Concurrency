package com.howtodoinjava.JavaConcurrencyTutorial._8_yieldAndJoin.yield;

public class Consumer extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i am consumer : " + i);
            /*
                A yielding thread tells the virtual machine that it’s willing to
                let other threads be scheduled in its place. This indicates that
                it’s not doing something too critical. Note that it’s only a hint,
                though, and not guaranteed to have any effect at all.
             */
            Thread.yield();
            /*
                Yield is a Static method and Native too.

                Yield tells the currently executing thread to give a chance
                to the threads that have equal priority in the Thread Pool.

                There is no guarantee that Yield will make the currently executing
                thread to runnable state immediately.

                It can only make a thread from Running State to Runnable State,
                not in wait or blocked state.
             */
        }
    }
}
