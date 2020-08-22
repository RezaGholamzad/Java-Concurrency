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
so it can only execute one task at a time.
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
An important point to consider is that the period between two executions is the period 
between these two executions that begins. If you have a periodic task that takes 5 seconds 
to execute, and you put a period of 3 seconds, you will have two instances of 
the task executing at a time.

For example, suppose I schedule an alarm to go off with a fixed rate of once an hour, 
and every time it goes off, I have a cup of coffee, which takes 10 minutes. 
Suppose that starts at midnight, I'd have:

00:00: Start making coffee
00:10: Finish making coffee
01:00: Start making coffee
01:10: Finish making coffee
02:00: Start making coffee
02:10: Finish making coffee

4) ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, 
TimeUnit unit) – Creates and executes a periodic action that becomes enabled first after 
the given initial delay, and subsequently with the given delay period between the termination 
of one execution and the commencement of the next. No matter how much time 
a long-running task takes, there will be a fixed delay time gap between two executions.

example : 
00:00: Start making coffee
00:10: Finish making coffee
01:10: Start making coffee
01:20: Finish making coffee
02:20: Start making coffee
02:30: Finish making coffee

#summery : 

1) The ThreadPoolExecutor class has four different constructors but, due to their complexity, 
the Java concurrency API provides the Executors class to construct executors and other 
related objects. Although we can create ThreadPoolExecutor directly using one of 
its constructors, it’s recommended to use the Executors class.

2) The cached thread pool, creates new threads if needed to execute the new tasks, 
and reuses the existing ones if they have finished the execution of the task 
they were running, which are now available. The cached thread pool has, however, 
a disadvantage of constant lying threads for new tasks, so if you send too many tasks 
to this executor, you can overload the system. This can be overcome using fixed thread pool.

3) One critical aspect of the ThreadPoolExecutor class, and of the executors in general, 
is that you have to end it explicitly. If you don’t do this, the executor will continue 
its execution, and the program won’t end. If the executor doesn’t have tasks to execute, 
it continues waiting for new tasks, and it doesn’t end its execution. A Java application 
won’t end until all its non-daemon threads finish their execution, so, if you don’t terminate 
the executor, your application will never end.

4) To indicate to the executor that you want to finish it, you can use the shutdown() method 
of the ThreadPoolExecutor class. When the executor finishes the execution of all pending tasks, 
it finishes its execution. After you call the shutdown() method, if you try to send another task 
to the executor, it will be rejected, and the executor will 
throw a RejectedExecutionException exception.

5) The ThreadPoolExecutor class provides a lot of methods to obtain information about its status. 
We used in the example the getPoolSize(), getActiveCount(), and getCompletedTaskCount() methods 
to obtain information about the size of the pool, the number of threads, and the number of completed 
tasks of the executor. You can also use the getLargestPoolSize() method that returns the maximum 
number of threads that has been in the pool at a time.

6) The ThreadPoolExecutor class also provides other methods related with the finalization 
of the executor. These methods are:

shutdownNow(): This method shutdowns the executor immediately. It doesn’t execute the pending tasks. 
It returns a list with all these pending tasks. The tasks that are running when you call this method 
continue with their execution, but the method doesn’t wait for their finalization.

isTerminated(): This method returns true if you have called the shutdown() or shutdownNow() methods 
and the executor finishes the process of shutting it down.

isShutdown(): This method returns true if you have called the shutdown() method of the executor.

awaitTermination(long timeout,TimeUnit unit): This method blocks the calling thread until the tasks 
of the executor have ended, or the timeout occurs. The TimeUnit class is an enumeration with 
the following constants: DAYS, HOURS, MICROSECONDS etc.




