package com.howtodoinjava.JavaConcurrencyTutorial._14_excutorFramework._1_excutor;

public class TaskOne implements Runnable{

    @Override
    public void run() {
        while (true)
        {
            System.out.println("Executing task one");
            try
            {
                Thread.sleep(1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
