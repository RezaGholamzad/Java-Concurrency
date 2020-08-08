package com.howtodoinjava._5_ObjectLevelLockVsClassLevelLock;

public class ClassLevel {

    //Method is static
    public synchronized static void demoMethod(){}

    public void demoMethod2()
    {
        //Acquire lock on .class reference
        synchronized (ClassLevel.class){
            //other thread safe code
        }
    }

    private final static Object lock = new Object();

    public void demoMethod3()
    {
        //Lock object is static
        synchronized (lock){
            //other thread safe code
        }
    }
}
