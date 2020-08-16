If you may know that in web-servers you can configure the maximum number of concurrent connections 
to the server. If more connections than this limit come to server, they have to wait until some 
other connections freed or closed. This limitation can be taken as throttling. 
Throttling is the capability of regulating the rate of input for a system where output 
rate is slower than input. It is necessary to stop the system from 
crashing or resource exhaustion.

creating a CustomThreadPoolExecutor which had following capabilities:

1) Tasks being submit to blocking queue

2) An executor which picks up the task from queue and execute them

3) Had overridden beforeExecute() and afterExecute() methods to perform 
some extra activities if needed

4) Attached a RejectedExecutionHandler which handle the task if it got 
rejected because the queue was full

Our approach was good enough already and capable of handling most of the practical scenarios. 
Now let’s add one more concept into it which may prove beneficial in some conditions. 
This concept is around throttling of task submission in the queue.

In this example, throttling will help in keeping the number of tasks in queue limit so 
that no task get rejected. It essentially removes the necessity 
of RejectedExecutionHandler as well.

In this solution, we will create a Semaphore with a number which must be equal to maximum 
number of tasks in blocking queue at any given point of time. So the approach works like this:

1) Before executing a task a lock in semaphore requested

2) If lock acquired then execution works normally; Otherwise 
retry will happen until lock acquired

3) Once task completed; lock released to semaphore