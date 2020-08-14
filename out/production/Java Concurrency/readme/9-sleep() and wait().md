sleep() is a method which is used to pause the process for few seconds, or 
the time we want, But in case of wait() method, thread goes in waiting state, 
and it won’t come back automatically until we call notify() or notifyAll().

The major difference is that wait() releases the lock or monitor while sleep() 
doesn’t release the lock or monitor while waiting. wait() is used for inter-thread 
communication while sleep() is used to introduce a pause on execution, generally.

Thread.sleep() sends the current thread into the “Not Runnable” state for some 
amount of time. The thread keeps the monitors it has acquired — i.e. if the threads 
currently in a synchronized block or method no other thread can enter this block 
or method. If another thread calls t.interrupt(). it will wake up the sleeping thread.

While sleep() is a static method which means that it always affects the current 
thread (the one is executing the sleep method). A common mistake is to call 
t.sleep() where t is a different thread; even then, it is the current thread 
that will sleep, not the t thread.

synchronized(LOCK) {   
    Thread.sleep(1000); // LOCK is hold
}

synchronized(LOCK) 
{   
    LOCK.wait(); // LOCK is not hold
}

#sleep() vs wait() Summary: 

Method called on : 

wait() – Call on an object; the current thread must synchronize on the lock object.

sleep() – Call on a Thread; always currently executing thread.

**************************

Synchronized : 

wait() – when synchronized multiple threads access same Object one by one.

sleep() – when synchronized multiple threads wait for sleep over of sleeping thread.

**************************

Lock duration : 

wait() – release the lock for other objects to have chance to execute.

sleep() – keep lock for at least t times if timeout specified or somebody interrupted.

**************************

wake up condition : 

wait() – until call notify(), notifyAll() from object

sleep() – until at least time expire or call interrupt().

**************************

Usage : 

sleep() – for time-synchronization

wait() – for multi-thread-synchronization.