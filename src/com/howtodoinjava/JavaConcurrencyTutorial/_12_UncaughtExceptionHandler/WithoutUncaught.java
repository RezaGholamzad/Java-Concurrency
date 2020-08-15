package com.howtodoinjava.JavaConcurrencyTutorial._12_UncaughtExceptionHandler;

public class WithoutUncaught {
    public static void main(String[] args) {

        new Thread(new WithoutUncaughtTask()).start();

//        new Thread(() -> {
//            System.out.println(Integer.parseInt("123"));
//            System.out.println(Integer.parseInt("234"));
//            System.out.println(Integer.parseInt("345"));
////            throws a java.lang.NumberFormatException” during it’s execution.
//            System.out.println(Integer.parseInt("XYZ")); //This will cause NumberFormatException
//            System.out.println(Integer.parseInt("456"));
//        }).start();
    }
}