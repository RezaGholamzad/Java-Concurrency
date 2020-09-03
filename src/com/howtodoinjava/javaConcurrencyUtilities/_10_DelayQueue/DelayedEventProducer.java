package com.howtodoinjava.javaConcurrencyUtilities._10_DelayQueue;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DelayedEventProducer implements Runnable{
    private final DelayQueue<DelayedEvent> queue;
    private final AtomicInteger counter;

    public DelayedEventProducer(DelayQueue<DelayedEvent> queue, AtomicInteger counter) {
        this.queue = queue;
        this.counter = counter;
    }

    @Override
    public void run() {
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime now = LocalDateTime.of(2020,9,3,23,20,0);
        int id = counter.incrementAndGet();
        DelayedEvent event = new DelayedEvent(id, "Task-" + id, now); // activation time = now
        System.out.println("Added to queue :: " + event);
        queue.add(event);
    }
}
