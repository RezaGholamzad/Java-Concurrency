package com.howtodoinjava.javaConcurrencyUtilities._12_CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker{

    public DatabaseHealthChecker(CountDownLatch latch) {
        super(latch, "database service");
    }

    @Override
    public void verifyService() {
        System.out.println("checking " + this.getServiceName());

        try {

            Thread.sleep(7000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getServiceName() + " is UP");
    }
}
