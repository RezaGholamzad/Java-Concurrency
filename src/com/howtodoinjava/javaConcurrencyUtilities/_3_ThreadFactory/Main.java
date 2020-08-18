package com.howtodoinjava.javaConcurrencyUtilities._3_ThreadFactory;

public class Main {
    public static void main(String[] args) {
        CustomThreadFactory factory = new CustomThreadFactory("CustomThreadFactory");
        Thread thread;
        Task task = new Task();
        System.out.println("starting the threads : ");
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.println("All Threads are created now");
        System.out.println("Give me CustomThreadFactory stats:" + factory.getStat());
    }
}
