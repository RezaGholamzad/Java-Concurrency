package com.howtodoinjava.javaConcurrencyUtilities._12_CountDownLatch;

import java.util.concurrent.CountDownLatch;

/*
    This class is a Runnable and parent for all specific external service health checkers.
    This remove the code duplicate and central control over latch.
 */
public abstract class BaseHealthChecker implements Runnable{

    private final CountDownLatch _latch;
    private final String _serviceName;
    private boolean _serviceUp;

    //Get latch object in constructor so that after completing the task, thread can countDown() the latch
    public BaseHealthChecker(CountDownLatch latch, String serviceName) {
        this._latch = latch;
        this._serviceName = serviceName;
        this._serviceUp = false;
    }

    public String getServiceName() {
        return _serviceName;
    }

    public boolean isServiceUp() {
        return _serviceUp;
    }

    @Override
    public void run() {

        try {
            verifyService();
            _serviceUp = true;
        }catch (Throwable t){
            t.printStackTrace(System.err);
            _serviceUp = false;
        }finally {
            /*
                Other N threads must have reference of latch object, because they will need
                to notify the CountDownLatch object that they have completed their task.
                This notification is done by method : CountDownLatch.countDown(); Each invocation
                of method decreases the initial count set in constructor, by 1. So, when all N
                threads have call this method, count reaches to zero, and main thread is
                allowed to resume its execution past await() method.
             */
            if (_latch != null){
                _latch.countDown();
            }
        }
    }

    //This method needs to be implemented by all specific service checker
    public abstract void verifyService();
}
