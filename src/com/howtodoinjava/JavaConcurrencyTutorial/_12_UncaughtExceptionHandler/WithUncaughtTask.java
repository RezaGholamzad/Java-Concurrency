package com.howtodoinjava.JavaConcurrencyTutorial._12_UncaughtExceptionHandler;

public class WithUncaughtTask implements Runnable{
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println(Integer.parseInt("321"));
        System.out.println(Integer.parseInt("234"));
        System.out.println(Integer.parseInt("345"));
        /*
            UncaughtExceptionHandler helps you to run a thread in a way such that it
            will run until it’s task is done. This can be achieved through other
            multi-threading concepts as well.

            Please note that UncaughtExceptionHandler can be used for making
            logging more robust only as well without restarting the thread
            because often default logs don’t provide enough information about
            the context when thread execution failed.
         */
        System.out.println(Integer.parseInt("XYZ")); //This will cause NumberFormatException
        System.out.println(Integer.parseInt("456"));
    }
}
