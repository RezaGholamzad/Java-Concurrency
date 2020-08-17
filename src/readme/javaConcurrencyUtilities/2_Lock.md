We are already aware of basic concepts around thread synchronization and various mechanisms 
using synchronized keyword. Java provides another mechanism for the synchronization of 
blocks of code based on the Lock interface and classes that implement 
it (such as ReentrantLock).

A java.util.concurrent.locks.Lock is a thread synchronization mechanism just like 
synchronized blocks. A Lock is, however, more flexible and more sophisticated 
than a synchronized block. Since Lock is an interface, you need to use one of 
its implementations to use a Lock in your applications. ReentrantLock is one 
such implementation of Lock interface :

Lock lock = new ReentrantLock();
 
lock.lock();
 
//critical section
 
lock.unlock();

First a Lock created. Then it’s lock() method is called. Now the Lock instance locked. 
Any other thread calling lock() will be blocked until the thread that locked the lock 
calls unlock(). Finally, unlock() is called, and the Lock now unlocked 
so other threads can lock it.

#Lock Interface vs synchronized keyword : 

 Having a timeout trying to get access to a synchronized block is not possible. 
 Using Lock.tryLock(long timeout, TimeUnit timeUnit), it is possible.
 
 The synchronized block must be fully contained within a single method. 
 A Lock can have its calls to lock() and unlock() in separate methods.
 
 *********************************
 
 You have to be very careful with the use of Locks to avoid deadlocks . 
 This situation occurs when two or more threads blocked waiting for locks 
 that will never be unlocked. For example, a thread (A) locks a Lock (X), and a 
 thread (B) locks a Lock (Y). If now, the thread (A) tries to lock the Lock (Y), and 
 the thread (B) simultaneously tries to lock the Lock (X), both threads will be 
 blocked indefinitely, because they are waiting for locks that will never be 
 liberated. Note that the problem occurs, because both threads try to get 
 the locks in the opposite order.

