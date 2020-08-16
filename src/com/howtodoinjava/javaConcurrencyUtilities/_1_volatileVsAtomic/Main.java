package com.howtodoinjava.javaConcurrencyUtilities._1_volatileVsAtomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        //Initial value is 0
        AtomicInteger atomicInteger = new AtomicInteger();
        //Initial value is 100
        AtomicInteger atomicInteger1 = new AtomicInteger(100);

        int currentValue = atomicInteger1.get();   //100
        atomicInteger.set(1234);

        boolean isSuccess = atomicInteger.compareAndSet(1234, 12);

        System.out.println(isSuccess);
    }
}
