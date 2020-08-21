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

