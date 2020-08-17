package com.howtodoinjava.javaConcurrencyUtilities._2_Lock;

/*
    You can submit a number of print jobs to printer during varying time interval
    or simultaneously. Printer will take a job from printer queue and print it.
    Rest of jobs will wait there for their turn. Once printer is done with print
    job in hand, it will pick another job from queue and start printing. Keep
    this happening in a loop.
 */
public class Main {

    public static void main(String[] args) {
        PrinterQueue printerQueue = new PrinterQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new PrintingJob(printerQueue), "thread " + i);
        }
        for (Thread t : thread){
            t.start();
        }
    }
}
