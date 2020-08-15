package com.howtodoinjava.JavaConcurrencyTutorial._11_CallableAndFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalculator implements Callable<Integer> {

    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    /*
        will override it’s call() method and after calculation, we will
        return the result from call() method. This result later can be
        retrieved from Future reference held by main program.
     */
    @Override
    public Integer call() throws Exception {
        int result = 1;
        if ( !(number == 1 || number == 0) ){
            for (int i = 2; i < number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.println("result for number " + number + " -> " +result);
        return result;
    }
}
