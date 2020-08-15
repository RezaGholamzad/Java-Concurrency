#A little background on java thread scheduling : 

A Java virtual machine is require to implement a preemptive, priority-based scheduler 
among its various threads. This means that each thread in a Java program is assign 
a certain priority, a positive integer that falls within a well-defined range. 
This priority can be changed by the developer. The Java virtual machine never 
changes the priority of a thread, even if the thread has been running for 
a certain period.

The priority value is important because the contract between the Java virtual machine, and 
the underlying operating system is that the operating system must generally choose to 
run the Java thread with the highest priority. That’s what we mean when we say that 
Java implements a priority-based scheduler. This scheduler is implement in preemptive fashion, 
to mean that when a higher-priority thread comes along, that thread interrupts (preempts) 
whatever lower-priority thread is running at the time. The contract with the operating system, 
however, is not absolute, which means that the operating system can sometimes choose to 
run a lower-priority thread.

Also, note that java does not mandate that its threads be time-sliced, but most 
operating systems do so. There is often some confusion in terminology here: 
preemption is often confuse with time-slicing. In fact, preemption means 
that a higher-priority thread runs instead of a lower-priority one, 
but when threads have the same priority, they do not preempt each other. 
They are typically subject to time-slicing, but that is not a requirement of Java.

#Understanding thread priorities : 

Remember that all the threads carry normal priority when a priority not specified.

Priorities can be specified from 1 to 10. 10 being the highest, 1 being 
the lowest priority and 5 being the normal priority.

Remember that the thread with the highest priority will be given preference in execution, 
But there is no guarantee that it will be in running state the moment it starts.

the currently Always executing thread might have the higher priority when compared to 
the threads in the pool who are waiting for their chance.

It is the thread scheduler which decides what thread should be executed.

t.setPriority() can be used to set the priorities for the threads.

Remember that the priorities should be set before the threads start method invoked.

You can use the constants, MIN_PRIORITY,MAX_PRIORITY and NORM_PRIORITY for setting priorities.