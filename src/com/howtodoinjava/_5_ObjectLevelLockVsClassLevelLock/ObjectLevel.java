package com.howtodoinjava._5_ObjectLevelLockVsClassLevelLock;

public class ObjectLevel {

    public synchronized void demoMethod(){}

    public void demoMethod1(){

        synchronized (this){
            //other thread safe code
        }
    }

    private final Object lock = new Object();
    public void demoMethod2(){

        synchronized (lock){
            //other thread safe code
        }
    }
}
