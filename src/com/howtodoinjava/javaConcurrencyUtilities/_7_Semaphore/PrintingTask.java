package com.howtodoinjava.javaConcurrencyUtilities._7_Semaphore;

public class PrintingTask implements Runnable{

    private final PrinterQueue printerQueue;

    public PrintingTask(PrinterQueue printerQueue) {
        this.printerQueue = printerQueue;
    }

    /*
        This class represents an independent printing job which could be submitted to printer queue.
        And from queue, it can be picked up by any printer and performed printing job.
        This class implements Runnable interface, so that printer can execute
        it when it’s turn come.
     */
    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread
                .currentThread().getName());
        printerQueue.printJob(new Object());
    }
}
