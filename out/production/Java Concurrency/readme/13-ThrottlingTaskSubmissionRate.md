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