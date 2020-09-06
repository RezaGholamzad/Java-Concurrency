The effective use of parallel cores in a Java program has always been a challenge. 
There were few home-grown frameworks that would distribute the work across multiple 
cores and then join them to return the result set. Java 7 has incorporated this feature 
as a Fork and Join framework.

Basically the Fork-Join breaks the task at hand into mini-tasks until the mini-task is 
simple enough that it can be solved without further breakups. It’s like a divide-and-conquer 
algorithm. One important concept to note in this framework is that ideally no worker thread 
is idle. They implement a work-stealing algorithm in that idle workers steal the work from 
those workers who are busy.

The core classes supporting the Fork-Join mechanism are ForkJoinPool and ForkJoinTask.

#ForkJoinPool:

The ForkJoinPool is basically a specialized implementation of ExecutorService implementing 
the work-stealing algorithm we talked about above. We create an instance of ForkJoinPool 
by providing the target parallelism level i.e. the number of processors as shown below:

ForkJoinPool pool = new ForkJoinPool(numberOfProcessors);

Where numberOfProcessors = Runtime.getRunTime().availableProcessors();

If you use a no-argument constructor, by default, it creates a pool of size that equals 
the number of available processors obtained using above technique.

Although you specify any initial pool size, the pool adjusts its size dynamically 
in an attempt to maintain enough active threads at any given point in time. 
Another important difference compared to other ExecutorService's is that this pool 
need not be explicitly shutdown upon program exit because all its threads are in daemon mode.

There are three different ways of submitting a task to the ForkJoinPool : 

1) execute() method //Desired asynchronous execution; call its fork method to split 
the work between multiple threads.

2) invoke() method: //Await to obtain the result; call the invoke method on the pool.

3) submit() method: //Returns a Future object that you can use for checking status and 
obtaining the result on its completion.

#Daemon thread in Java? 

Daemon thread is a low priority thread that runs in background to perform tasks 
such as garbage collection.

Properties:

They cannot prevent the JVM from exiting when all the user threads 
finish their execution.

JVM terminates itself when all user threads finish their execution

If JVM finds running daemon thread, it terminates the thread and after that shutdown itself. 
JVM does not care whether Daemon thread is running or not.

It is an utmost low priority thread.

#Daemon vs User Threads : 

Priority: When the only remaining threads in a process are daemon threads, the interpreter exits. 
These makes sense because when only daemon threads remain, there is no other thread for which 
a daemon thread can provide a service.

Usage: Daemon thread is to provide services to user thread for background supporting task.

#ForkJoinTask : 

This is an abstract class for creating tasks that run within a ForkJoinPool. 
The RecursiveAction and RecursiveTask are the only two direct, known subclasses of ForkJoinTask. 
The only difference between these two classes is that the RecursiveAction does not return 
a value while RecursiveTask does have a return value and returns an object of specified type.

In both cases, you would need to implement the compute method in your subclass that performs 
the main computation desired by the task.

The ForkJoinTask class provides several methods for checking the execution status of a task. 
The isDone() method returns true if a task completes in any way. The isCompletedNormally() 
method returns true if a task completes without cancellation or encountering an exception, 
and isCancelled() returns true if the task was cancelled. Lastly, isCompletedabnormally() 
returns true if the task was either cancelled or encountered an exception.





