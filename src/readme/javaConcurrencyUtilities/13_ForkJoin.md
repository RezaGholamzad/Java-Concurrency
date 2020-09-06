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

#How example works?

In the FolderProcessor class, Each task processes the content of a folder. 
As you know, this content has the following two kinds of elements:

+Files

+Other folders

If the task finds a folder, it creates another Task object to process that folder and 
sends it to the pool using the fork() method. This method sends the task to the pool 
that will execute it if it has a free worker-thread or it can create a new one. 
The method returns immediately, so the task can continue processing the content 
of the folder. For every file, a task compares its extension with the one it’s 
looking for and, if they are equal, adds the name of the file to the list of results.

Once the task has processed all the content of the assigned folder, it waits for 
the finalization of all the tasks it sent to the pool using the join() method. 
This method called in a task waits for the finalization of its execution and 
returns the value returned by the compute() method. The task groups the results 
of all the tasks it sent with its own results and returns that list as a return 
value of the compute() method.

#Difference between Fork/Join Framework And ExecutorService : 

The main difference between the Fork/Join and the Executor frameworks is 
the work-stealing algorithm. Unlike the Executor framework, when a task is 
waiting for the finalization of the sub-tasks it has created using the join operation, 
the thread that is executing that task (called worker thread ) looks for other tasks 
that have not been executed yet and begins its execution. By this way, the threads take 
full advantage of their running time, thereby improving the performance of the application.

Fork/Join framework uses work-stealing algorithm. Work stealing is a scheduling 
strategy where worker threads that have finished their own tasks can steal pending 
tasks from other threads. In parallel execution, tasks are divided among multiple 
processors/cores. When a core has no work, it should be assigned a task from 
another processor's overloaded queue rather than being idle.

#newWorkStealingPool() : 

Now, this could be done using more feature packed Executer and ExecutorService interfaces. 
These interfaces provided a mechanism where a developer can focus on the tasks to be performed 
concurrently, letting these interfaces take care of Thread creation and their lifecycle management. 
At the core of these interfaces are the Thread pools, which provide a pool of these worker 
threads that are used to perform tasks. 

In Java 8, a new type of thread pool is introduced as newWorkStealingPool() to complement 
the existing ones. Java gave a very succinct definition of this pool as:

“Creates a work-stealing thread pool using all available processors as its target parallelism level.”

Let’s explore this pool in more detail and see what it brings to our development toolbox.

As its name says, it’s based on a work-stealing algorithm, where a task can spawn other, 
smaller tasks, which are added to queues of parallel processing threads. If one thread has 
finished its work and has nothing more to do, it can “steal” the work from the 
other thread’s queue.

But this work-stealing mechanism is already used by ForkJoinPool in Java and is highly 
useful when your task(s) spawn smaller tasks, which can be proactively picked up by 
any available thread, reducing the thread idle time. So what's so new in this 
ExecutorService's work-stealing mechanism?

this newWorkStealingPool is returning a ForkJoinPool only, but with some preconfigured parameters:

public static ExecutorService newWorkStealingPool() {

    return new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
    ForkJoinPool.defaultForkJoinWorkerThreadFactory,null, true);
    
}

Runtime.getRuntime().availableProcessors() – This is the number of processors available to the JVM.

ForkJoinPool.defaultForkJoinWorkerThreadFactory – Default thread factory to return new threads.

null - Thread.UncaughtExceptionHandler passed as null

true – This makes it work in aysnc mode and sets the FIFO order for forked tasks, 
which are never joined from its work queue.

So to conclude, the newWorkStealingPool provided in Java 8 is not new at all — 
it just provides a level of abstraction over ForkJoinPool. But yes, it definitely 
reduces the lines of code I need if my requirement is pretty specific to asynchronous 
bursts of tasks, requiring all available processors as the target parallelism 
with minimum thread ideal time.

#Existing Implementations in JDK : 

1) One such implementation, introduced in Java SE 8, is used by the java.util.Arrays 
class for its parallelSort() methods. These methods are similar to sort(), 
but leverage concurrency via the fork/join framework. Parallel sorting of large arrays 
is faster than sequential sorting when run on multiprocessor systems.

2) Parallelism used in Stream.parallel().
