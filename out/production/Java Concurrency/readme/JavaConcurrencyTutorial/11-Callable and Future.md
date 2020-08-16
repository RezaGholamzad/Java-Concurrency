One of the benefits of the Java executor framework is that we can run concurrent tasks 
that may return a single result after processing the tasks. The Java Concurrency API 
achieves this with the following two interfaces Callable and Future.

#Callable : 

the Callable interface has the call() method. In this method, we have to implement the logic 
of a task. The Callable interface is a parameterized interface, meaning we have 
to indicate the type of data the call() method will return.

#Future :

the Future interface has methods to obtain the result generated 
by a Callable object and to manage its state.
