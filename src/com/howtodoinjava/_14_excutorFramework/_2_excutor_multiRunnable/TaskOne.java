package com.howtodoinjava._14_excutorFramework._2_excutor_multiRunnable;

public class TaskOne implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing Task One " + Thread.currentThread());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
