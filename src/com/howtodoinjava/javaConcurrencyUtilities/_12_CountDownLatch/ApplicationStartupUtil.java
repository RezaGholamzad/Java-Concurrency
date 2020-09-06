package com.howtodoinjava.javaConcurrencyUtilities._12_CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface ApplicationStartupUtil {

    static boolean checkExternalService() throws InterruptedException {

        //Initialize the latch with number of service checkers
        List<BaseHealthChecker> services = new ArrayList<>();

        /*
            This count is essentially the number of threads, for which latch should wait.
            This value can be set only once, and CountDownLatch provides no other mechanism
            to reset this count.
         */
        CountDownLatch latch = new CountDownLatch(3);

        //All add checker in lists
        services.add(new NetworkHealthChecker(latch));
        services.add(new CacheHealthChecker(latch));
        services.add(new DatabaseHealthChecker(latch));

        //Start service checkers using executor framework
        ExecutorService executors = Executors.newFixedThreadPool(services.size());

        for (BaseHealthChecker service : services){
            executors.execute(service);
        }

        /*
            The first interaction with CountDownLatch is with main thread which is going to
            wait for other threads. This main thread must call, CountDownLatch.await()
            method immediately after starting other threads. The execution will stop on await()
            method till the time, other threads complete their execution.
         */
        latch.await();

        //services are file and now proceed startup
        for (BaseHealthChecker service : services){
            if (!service.isServiceUp()){
                return false;
            }
        }
        return true;
    }

}
