we will learn to use such a new feature ThreadPoolExecutor in combination with BlockingQueue. 
We will see the best practices for ThreadPoolExecutor and BlockingQueue classes 
to use in our application.

#BlockingQueue : 

If you remember solving the producer-consumer problem, before JDK 5, consumer had to 
wait until producer put something in resource queue. This problem can be easily 
solved using new BlockingQueue.

BlockingQueue is like another Queue implementations with additional capabilities. 
Any attempt, to retrieve something out of it, can be seen safe as it will not return 
empty-handed. Consumer thread will automatically wait until BlockingQueue is not populated 
with some data. Once it fills, the thread will consume the resource.

BlockingQueue works on following rules:

1) If fewer than corePoolSize threads are running, the Executor always prefers adding a 
new thread rather than queuing.

2) If corePoolSize or more threads are running, the Executor always prefers queuing a 
request rather than adding a new thread.

3) If a request cannot be queued, a new thread created unless this would exceed maximumPoolSize, 
in which case, the task will be rejected.

what is mean :

Java Thread Pool Executor (TPE) is biased toward queuing rather than spawning new threads, i.e., 
after the initial core threads get occupied, tasks gets added to queue, and after the queue 
reaches its limit (which would happen only for bounded queues), extra threads would be spawned. 
If the queue is unbounded, then extended threads won’t get spawned at all.

1)Initial core threads created to handle the load.

2)Once there are more tasks than the number of core threads, 
the queue starts filling up to store the tasks.

3)Once the queue filled, extended threads are create.

