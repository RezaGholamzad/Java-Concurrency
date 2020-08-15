package com.howtodoinjava.JavaConcurrencyTutorial._7_WaitNotifyNotifyAll;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*
            producer consumer problem :

            only one producer and one consumer thread.

            Producer thread produce a new resource in every 1 second and put it in ‘taskQueue’.

            Consumer thread takes 1 seconds to process consumed resource from ‘taskQueue’.

            Max capacity of taskQueue is 5 i.e. maximum 5 resources can exist inside ‘taskQueue’ at any given time.

            Both threads run infinitely.
         */
        List<Integer> taskQueue = new ArrayList<>();
        int MAX_CAPACITY = 5;
        Thread producerThread = new Thread(new Producer(taskQueue, MAX_CAPACITY), "producer");
        Thread consumerThread = new Thread(new Consumer(taskQueue), "consumer");

        producerThread.start();
        consumerThread.start();
    }
}
