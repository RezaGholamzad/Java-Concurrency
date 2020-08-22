package com.howtodoinjava.javaConcurrencyUtilities._7_Semaphore;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    This class represent the printer queue/ printer. This class has 3 main attributes which
    control the logic of selecting a free printer out of 3 printers and lock it for
    printing a job. After printing the document, printer is released so that it is
    again free and available for printing a new job from print queue.
 */
public class PrinterQueue {

    //This variable keep track of no. of printers used at any point of time.
    private final Semaphore semaphore;

    /*
        Used for locking the printer pool before checking/acquiring a free printer
        out of three available printers.
     */
    private final Lock printerLock;

    private final boolean[] freePrinters;

    public PrinterQueue() {
        /*
            To demonstrate the concept, we will be using semaphore for controlling 3 printers
            which can print multiples documents simultaneously.

            the Semaphore object is created using 3 as the parameter of the constructor.
            The first three threads that call the acquire() method will get the access
            to printers while the rest will be blocked. When a thread finishes the critical
            section and releases the semaphore, another thread will acquire it.

         */
        this.semaphore = new Semaphore(3);
        this.printerLock = new ReentrantLock();
        freePrinters = new boolean[3];
        Arrays.fill(freePrinters, true);
    }

    public void printJob(Object document){
        try {

            // decrease the semaphore counter to mark a printer busy
            semaphore.acquire();

            //Get the free printer
            int foundPrinter = getPrinter();

            //Print the job
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()
                    + ": Printer " + foundPrinter
                    + " : Printing a Job during " + (duration / 1000)
                    + " seconds :: Time - " + new Date());
            Thread.sleep(duration);

            //Printing is done; Free the printer to be used by other threads.
//            if (foundPrinter != -1) // remove comment if you commented semaphore
                releasePrinter(foundPrinter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.printf("%s: The document has been printed\n", Thread
                    .currentThread().getName());

            //Increase the semaphore counter back
            semaphore.release();
        }

    }

    private int getPrinter(){

        int foundPrinter = -1;

        try {
            //Get a lock here so that only one thread can go beyond this at a time
            printerLock.lock();

            //Check which printer is free
            for (int i = 0; i < freePrinters.length; i++) {

                //If free printer found then mark it busy
                if (freePrinters[i]){
                    foundPrinter = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            //Allow other threads to check for free priniter
            printerLock.unlock();
        }

        return foundPrinter;
    }

    //release the printer
    private void releasePrinter(int i){
        printerLock.lock();
        //mark the printer free
        freePrinters[i] = true;
        printerLock.unlock();
    }
}
