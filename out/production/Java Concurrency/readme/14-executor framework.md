Java executor framework (java.util.concurrent.Executor), released with the JDK 5 is used 
to run the Runnable objects without creating new threads every time and mostly 
re-using the already created threads.

We all know about that there are two ways to create a thread in java.
Creating a thread in java is a very expensive process which includes memory 
overhead as well. So, it’s a good idea if we can re-use these threads once created, 
to run our future runnables.


