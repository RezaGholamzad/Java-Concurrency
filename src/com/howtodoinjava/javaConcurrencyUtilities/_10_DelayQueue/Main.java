package com.howtodoinjava.javaConcurrencyUtilities._10_DelayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        DelayQueue<DelayedEvent> queue = new DelayQueue<>();

        AtomicInteger counter = new AtomicInteger();

        /*
            I have re-written the producer consumer problem using ScheduledExecutorService.
            In this program, producer thread is adding events in a DelayQueue. Consumer thread
            invokes periodically and picks up all items that have expired activation
            time i.e. in past.
         */
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.scheduleAtFixedRate(new DelayedEventProducer(queue, counter),
                1, 2, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new DelayedEventConsumer(queue),
                1, 10, TimeUnit.SECONDS);
    }
}
