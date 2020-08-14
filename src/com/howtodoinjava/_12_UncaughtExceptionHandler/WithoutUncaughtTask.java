package com.howtodoinjava._12_UncaughtExceptionHandler;

public class WithoutUncaughtTask implements Runnable{
    @Override
    public void run() {
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.parseInt("234"));
        System.out.println(Integer.parseInt("345"));
        /*
            throws a java.lang.NumberFormatException” during it’s execution.
            As program does not try to catch this exception, exception floats through JVM level
            and thread gets killed. This is absolutely normal behavior but it
            MAY NOT be desired behavior.
            the thread to die immediately after failure.
         */
        System.out.println(Integer.parseInt("XYZ")); //This will cause NumberFormatException
        System.out.println(Integer.parseInt("456"));
    }
}
