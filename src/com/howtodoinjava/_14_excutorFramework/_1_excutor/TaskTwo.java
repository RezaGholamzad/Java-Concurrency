package com.howtodoinjava._14_excutorFramework._1_excutor;

public class TaskTwo implements Runnable{

    @Override
    public void run() {
        while (true)
        {
            System.out.println("Executing task two");
            try
            {
                Thread.sleep(1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
