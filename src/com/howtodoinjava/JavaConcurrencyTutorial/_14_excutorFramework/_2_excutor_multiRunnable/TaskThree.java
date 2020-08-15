package com.howtodoinjava.JavaConcurrencyTutorial._14_excutorFramework._2_excutor_multiRunnable;

public class TaskThree implements Runnable{
    @Override
    public void run() {

        System.out.println("Executing Task Three " + Thread.currentThread());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
