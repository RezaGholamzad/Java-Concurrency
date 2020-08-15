package com.howtodoinjava.JavaConcurrencyTutorial._4_synchronizedKeyword;

public class Main {

    public static void main(String[] args) {

        final Math math = new Math();

        //first thread
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    math.printNumbers(3);
//                    math.printNumbers2(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable, "ONE").start();
        new Thread(runnable, "TWO").start();

//        in java 8
//        new Thread(() -> {
//            try {
//                math.printNumbers(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

    }
}
