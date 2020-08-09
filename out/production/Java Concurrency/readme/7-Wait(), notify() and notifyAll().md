Programmers using concurrency classes will feel a lot more confident than programmers directly 
handling synchronization stuff using wait(), notify() and notifyAll() method calls. 
I will also recommend using these newer APIs over synchronization yourself, 
BUT many times we are required to do so for various reasons e.g. maintaining legacy code. 
Good knowledge around these methods will help you in such a situation when arrived.

The Object class in Java has three final methods that allow threads to communicate 
about the locked status of a resource.

#wait() : 

It tells the calling thread to give up the lock and go to sleep until some other thread 
enters the same monitor and calls notify(). The wait() method releases the lock prior 
to waiting and reacquires the lock prior to returning from the wait() method. 
The wait() method actually tightly integrated with the synchronization lock, 
using a feature not available directly from the synchronization mechanism.

In other words, it is not possible for us to implement the wait() method 
purely in Java. It is a native method.

#notify() :

It wakes up one single thread that called wait() on the same object. It should be noted 
that calling notify() does not actually give up a lock on a resource. 
it tells a waiting thread that that thread can wake up. However, 
the lock is not actually give up until the notifier’s 
synchronized block has completed.

So, if a notifier calls notify() on a resource, but the notifier still needs 
to perform 10 seconds of actions on the resource within its synchronized block, 
the thread that had been waiting will need to wait at least another 
additional 10 seconds for the notifier to release the lock on the object, 
even though notify() had been called.

#notifyAll() :

It wakes up all the threads that called wait() on the same object. 
The highest priority thread will run first in most of the situation, 
though not guaranteed. Other things are same as notify() method above.