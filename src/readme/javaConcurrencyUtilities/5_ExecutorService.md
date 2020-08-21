Learn to use Java ExecutorService to execute a Runnable or Callable class in asynchronous way.

#Executor Framework : 

In simple Java applications, we do not face much challenge while working with some threads. 
If you have to develop a program that runs a lot of concurrent tasks, this approach will 
present many disadvantages such as lots of boiler plate code (create and manage threads), 
executing thread manually and keeping track of thread execution results.

Executor framework solved this problem. The framework consist of three main interfaces 
(and lots of child interfaces) i.e. Executor, ExecutorService and ThreadPoolExecutor.

Benefits of Executor framework : 

The framework mainly separates task creation and execution. Task creation is mainly boiler plate code 
and is easily replaceable.

With an executor, we have to create tasks which implement either Runnable or Callable interface 
and send them to the executor.

Executor internally maintain a (configurable) thread pool to improve application performance by 
avoiding the continuous spawning of threads.

Executor is responsible for executing the tasks, running them with the necessary 
threads from the pool.

Callable and Future : 

Another important advantage of the Executor framework was the Callable interface. 
It’s similar to the Runnable interface with two benefits:

It’s call() method returns a result after the thread execution is complete.

When we send a Callable object to an executor, we get a Future object’s reference. 
We can use this object to query the status of thread and the result of the Callable object.

#Creating ExecutorService instances : 

ExecutorService is an interface, and it’s implementations can execute a Runnable or 
Callable class in asynchronous way. Note that invoking the run() method of a Runnable interface 
in synchronous way is simply calling a method.

1) Executors class : 

Executors is a utility class that provides factory methods for creating 
the implementations of the ExecutorService interface : 

//Executes only one thread
ExecutorService es = Executors.newSingleThreadExecutor(); 
 
//Internally manages thread pool of 2 threads
ExecutorService es = Executors.newFixedThreadPool(2); 
 
//Internally manages thread pool of 10 threads to run scheduled tasks
ExecutorService es = Executors.newScheduledThreadPool(10); 

2) Constructors : 

We can choose an implementation class of ExecutorService interface and create its instance directly. 
Below statement creates a thread pool executor with minimum thread count 10, maximum threads count 100, 
5 milliseconds keep alive time, and a blocking queue to watch for tasks in the future.

ExecutorService executorService = 
    new ThreadPoolExecutor(10, 100, 5L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    
#shutdown ExecutorService : 

The final and most important thing that many developers miss is shutting down the ExecutorService. 
The ExecutorService is created, and it has Thread elements.

Remember that the JVM stops only when all non-daemon threads stopped. Here not shutting down 
the executor service simply prevents the JVM from stopping.

In above examples, if we comment out the executor.shutdownNow() method call, then even after all 
tasks executed, main thread remains active and JVM does not stop.

To tell the executor service that there is no need for the threads it has, we will 
have shutdown the service.

There are three methods to invoke the shutdown:

1) void shutdown() – Initiates an orderly shutdown in which previously submitted 
tasks executed, but no new tasks will be accepted.

2) List<Runnable> shutdownNow() – Attempts to stop all actively executing tasks, 
halts the processing of waiting tasks, and returns a list of the tasks that 
were awaiting execution.

3) void awaitTermination() – It blocks until all tasks have completed execution after a 
shutdown request, or the timeout occurs, or the current thread interrupted, 
whichever happens first.

#Conclusion : 

As discussed above, helps in minimizing the boiler plate code which is a good thing. 
It also helps in better resource management by internally utilizing 
a thread pool.ExecutorService

Still, programmers should be careful to avoid some common mistakes. E.g. always shutdown 
the executor service after tasks completed and service is no longer needed. 
Otherwise, JVM will never terminate, normally.

Similarly, while creating its instance, be mindful of the configured thread pool capacity. 
Here or any other implementation, a careless thread pool size can halt the system and 
bring performance down.

