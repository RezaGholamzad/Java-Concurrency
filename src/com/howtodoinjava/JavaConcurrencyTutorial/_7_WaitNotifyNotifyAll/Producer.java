package com.howtodoinjava.JavaConcurrencyTutorial._7_WaitNotifyNotifyAll;

import java.util.List;

public class Producer implements Runnable{

    private final List<Integer> taskQueue;
    private final int MAX_CAPACITY;

    public Producer(List<Integer> taskQueue, int MAX_CAPACITY) {
        this.taskQueue = taskQueue;
        this.MAX_CAPACITY = MAX_CAPACITY;
    }

    private void producer(int i) throws InterruptedException {
        synchronized (taskQueue){
            while (taskQueue.size() == MAX_CAPACITY){
                System.out.println("Queue is full " +
                        Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(1000);
            taskQueue.add(i);
            System.out.println("producer : " + i);
            taskQueue.notifyAll();
        }
    }

    @Override
    public void run() {
        int counter = 0;
        while (true){
            try {
                //producer keeps producing elements at regular interval.
                producer(counter++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
