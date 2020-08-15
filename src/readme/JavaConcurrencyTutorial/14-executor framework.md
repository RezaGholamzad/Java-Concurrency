Java executor framework (java.util.concurrent.Executor), released with the JDK 5 is used 
to run the Runnable objects without creating new threads every time and mostly 
re-using the already created threads.

We all know about that there are two ways to create a thread in java.
Creating a thread in java is a very expensive process which includes memory 
overhead as well. So, it’s a good idea if we can re-use these threads once created, 
to run our future runnables.

#java executor framework best practices : 

Always run your java code against static analysis tools like PMD and FindBugs to look 
for deeper issues. They are very helpful in determining ugly situations which 
may arise in the future.

Always cross-check and better plan a code review with senior guys to detect and 
possible deadlock or livelock in code during execution. Adding a health monitor 
in your application to check the status of running tasks is an excellent choice 
in most of the scenarios.

In multi-threaded programs, make a habit of catching errors too, not just exceptions. 
Sometimes unexpected things happen and Java throws an error at you, apart from an exception.

Use a back-off switch, so if something goes wrong and is non-recoverable, 
you don’t escalate the situation by eagerly starting another loop. 

Instead, you need to wait until the situation goes back to normal and then start again.

Please note that the whole point of executors is to abstract away the specifics of execution, 
so ordering not guaranteed unless explicitly stated.
