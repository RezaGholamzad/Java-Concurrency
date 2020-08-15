package com.howtodoinjava._14_excutorFramework._1_excutor;

public class Main {

    public static void main(String[] args) {
        while (true)
        {
            try
            {
                CheckTask.check();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Caught exception: " + e.getMessage());
            }
        }
    }
}
