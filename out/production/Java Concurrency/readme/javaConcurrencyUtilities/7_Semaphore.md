can be used to control access to single copy of a resource using the counter
value either 0 or 1. However, semaphores can also be used when you need to protect
various copies of a resource that can be executed by more than one thread
at the same time.

#How Semaphores Work? :

You can visualize a semaphore as counter which can be incremented or decremented. 
You initialize the semaphore with a number i.e. 5. Now this semaphore can be decremented 
maximum five times in a row until counter reaches to 0. Once counter is zero, 
you can increment it to maximum five times to make it 5. The counter value of 
semaphore MUST always be inside limit 0 <= n >= 5 (in our case).

Obviously, semaphores are more than just being counters. They are able to make 
threads wait when counter value is zero i.e. they act as Locks with 
counter functionality.

Talking in terms of multi-threading, when a thread wants to access one of 
shared resources (guarded by semaphore), first, it must acquire the semaphore. 
If the internal counter of the semaphore is greater than 0, the semaphore 
decrements the counter and allows access to the shared resource. Otherwise, 
if the counter of the semaphore is 0, the semaphore puts the thread to sleep 
until the counter is greater than 0. A value of 0 in the counter means all 
the shared resources used by other threads, so the thread that wants to use 
one of them must wait until one is free.

When a thread has finished the use of the shared resource, it must release 
the semaphore so that the other threads can access the shared resource. 
That operation increases the internal counter of the semaphore.

