CountDownLatch is a synchronization aid that allows one or more threads to wait until a 
set of operations being performed in other threads completes.

#What is CountDownLatch?

This class enables a java thread to wait until other set of threads completes their tasks. 
e.g. Application’s main thread want to wait, till other service threads which are responsible 
for starting framework services have completed started all services.

CountDownLatch works by having a counter initialized with number of threads, which is 
decremented each time a thread complete its execution. When count reaches to zero, 
it means all threads have completed their execution, and thread waiting on latch 
resume the execution.

#possible usages in real time applications: 

1) Achieving Maximum Parallelism : Sometimes we want to start a number of threads at the same time 
to achieve maximum parallelism. For example, we want to test a class for being singleton. 
This can be done easily if we create a CountDownLatch with initial count 1, and make wait 
all threads to wait of latch. A single call to countDown() method will resume execution 
for all waiting threads in same time.

2) Wait N threads to completes before start execution: For example an application start-up 
class want to ensure that all N external systems are Up and running before handling 
the user requests.

3) Deadlock detection: A very handy use case in which you can use N threads to access a 
shared resource with different number of threads in each test phase, and try to create 
a deadlock.