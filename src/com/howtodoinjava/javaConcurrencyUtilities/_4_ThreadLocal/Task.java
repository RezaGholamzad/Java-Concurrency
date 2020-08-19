package com.howtodoinjava.javaConcurrencyUtilities._4_ThreadLocal;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Runnable{

    /*
        Below example uses two thread local variables i.e. threadId and startDate.
        Both have been defined as “private static” fields as recommended. ‘threadId‘
        will be used to identify the thread which is currently running
     */
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = ThreadLocal
            .withInitial(nextId::getAndIncrement);

    public int getThreadId() {
        return threadId.get();
    }

    // SimpleDateFormat is not thread-safe, so give one to each thread
    private static final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    /*
        As you can see from the output that Thread-0 has changed the value of formatter but
        still thread-2 default formatter is same as the initialized value. You can see the
        same pattern for other threads too.
     */
    @Override
    public void run() {
        System.out.printf("default Formatter: %s : %s\n", getThreadId(), dateFormatThreadLocal.get().toPattern());
        try
        {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        dateFormatThreadLocal.set(new SimpleDateFormat());
        System.out.printf("Formatter: %s : %s\n", getThreadId(), dateFormatThreadLocal.get().toPattern());
    }
}
