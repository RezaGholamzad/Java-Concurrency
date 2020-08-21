Why you need thread pool in Java? Answer is usually when you develop a simple, 
concurrent application in Java, you create some Runnable objects and then 
create the corresponding Thread objects to execute them. Creating a thread in 
Java is an expensive operation. And if you start creating new thread instance 
every time to execute a task, application performance will degrade surely.

#How thread pool works : 

A thread pool is a collection of pre-initialized threads. Generally, the size of 
the collection fixed, but it is not mandatory. It facilitates the execution of N 
number of tasks using the same threads. If there are more tasks than threads, 
then tasks need to wait in a queue like structure (FIFO – First in first out).

When any thread completes its execution, it can pickup a new task from the queue 
and execute it. When all tasks completed the threads remain active and wait 
for more tasks in the thread pool.

A watcher keep watching queue (usually BlockingQueue) for any new tasks. 
As soon as tasks come, threads again start picking up tasks and execute them.

#ThreadPoolExecutor : 

the Java concurrency API provides a mechanism Executor framework. This is around 
the Executor interface, its sub-interface ExecutorService, and the ThreadPoolExecutor 
class that implements both interfaces.

ThreadPoolExecutor separates the task creation and its execution. With ThreadPoolExecutor, 
you only have to implement the Runnable objects and send them to the executor. 
It is responsible for their execution, instantiation, and running with necessary threads.

It goes beyond that and improves performance using a pool of threads. 
When you send a task to the executor, it tries to use a pooled thread for 
the execution of this task, to avoid continuous spawning of threads.

#Create ThreadPoolExecutor : 

We can create following 5 types of thread pool executors with pre-built methods in 
java.util.concurrent.Executors interface: 

1) Fixed thread pool executor – Creates a thread pool that reuses a fixed number 
of threads to execute any number of tasks. If additional tasks submitted when 
all threads are active, they will wait in the queue until a thread is available. 
It is the best fit for most off the real-life use-cases : 

ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

2) Cached thread pool executor – Creates a thread pool that creates new threads as 
needed, but will reuse previously constructed threads when they are available. 
DO NOT use this thread pool if tasks are long-running. It can bring down the system 
if the number of threads goes beyond what the system can handle : 

ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

3) Scheduled thread pool executor – Creates a thread pool that can schedule commands 
to run after a given delay, or to execute periodically : 

ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newScheduledThreadPool(10);

4) Single thread pool executor – Creates single thread to execute all tasks. Use it 
when you have only one task to execute : 

ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();

5) Work stealing thread pool executor – Creates a thread pool that maintains enough threads 
to support the given parallelism level. Here parallelism level means the maximum number 
of threads which will be used to execute a given task, at a single point of time, 
in multi-processor machines.

ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newWorkStealingPool(4);

#ScheduledThreadPoolExecutor : 

Fixed thread pools or cached thread pools are good when you have to execute one unique 
task only once. When you need to execute a task, repeatedly N times, either N fixed number 
of times or infinitely after fixed delay, you should be using ScheduledThreadPoolExecutor.

ScheduledThreadPoolExecutor provides 4 methods which provide different capabilities to execute 
the tasks in repeated manner : 

1) ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit) – Creates and executes 
a task that becomes enabled after the given delay.

2) ScheduledFuture schedule(Callable callable, long delay, TimeUnit unit) – Creates and executes 
a ScheduledFuture that becomes enabled after the given delay.

3) ScheduledFuture scheduleAtFixedRate(Runnable command, long initialDelay, 
long delay, TimeUnit unit) – Creates and executes a periodic action that becomes enabled 
first after the given initial delay, and subsequently with the given delay period. 
If any execution of this task takes longer than its period, then subsequent executions 
may start late, but will not concurrently execute.

4) ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, 
TimeUnit unit) – Creates and executes a periodic action that becomes enabled first after 
the given initial delay, and subsequently with the given delay period. No matter how much time 
a long-running task takes, there will be a fixed delay time gap between two executions.



