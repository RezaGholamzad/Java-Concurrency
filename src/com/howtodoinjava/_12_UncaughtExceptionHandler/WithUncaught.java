package com.howtodoinjava._12_UncaughtExceptionHandler;

public class WithUncaught {
    public static void main(String[] args) {
        new Thread(new WithUncaughtTask()).start();
    }
}
