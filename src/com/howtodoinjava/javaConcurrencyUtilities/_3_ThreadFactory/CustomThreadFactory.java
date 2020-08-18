package com.howtodoinjava.javaConcurrencyUtilities._3_ThreadFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {
    private int counter;
    private final String name;
    private final List<String> stats;

    public CustomThreadFactory(String name) {
        this.counter = 1;
        this.name = name;
        this.stats = new ArrayList<>();
    }


    /*
        Here, the ThreadFactory interface has only one method called newThread().
        It receives a Runnable object as a parameter and returns a Thread object.
        When you implement a ThreadFactory interface, you have to implement that
        interface and override this method.
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-thread-" + counter);
        counter++;
        stats.add(String.format("created thread %d with name %s on %s \n",
                t.getId(), t.getName(), new Date()));
        return t;
    }

    public String getStat(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String stat : stats) {
            stringBuilder.append(stat);
        }
        return stringBuilder.toString();
    }
}
