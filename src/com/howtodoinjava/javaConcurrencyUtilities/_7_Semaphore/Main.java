package com.howtodoinjava.javaConcurrencyUtilities._7_Semaphore;

public class Main {
    public static void main(String[] args) {
        PrinterQueue printerQueue = new PrinterQueue();

        Thread[] threads= new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new PrintingTask(printerQueue), "thread " + i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
