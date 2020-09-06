package com.howtodoinjava.javaConcurrencyUtilities._12_CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker{

    public CacheHealthChecker(CountDownLatch latch) {
        super(latch, "cache service");
    }

    @Override
    public void verifyService() {
        System.out.println("checking " + this.getServiceName());

        try {

            Thread.sleep(7000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getServiceName() + "is UP");
    }
}
