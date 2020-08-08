#Concurrency : 

Concurrency is essentially applicable when we talk about minimum two tasks or more. 
When an application is capable of executing two tasks virtually at same time, 
we call it concurrent application. Though here tasks run looks like simultaneously, 
but essentially they MAY not. They take advantage of CPU time-slicing feature of 
operating system where each task run part of its task and then go to waiting state. 
When first task is in waiting state, CPU assigned to second task 
to complete it’s part of task.

Operating system based on priority of tasks, thus, assigns CPU and other computing resources 
e.g. memory; turn by turn to all tasks and give them chance to complete. 
To end user, it seems that all tasks are running in parallel. This is called concurrency.

#Parallelism : 

Parallelism is when multiple tasks OR several parts of a unique task 
literally run at the same time, e.g. on a multi-core processor. 

Parallelism does not require two tasks to exist. It literally physically run parts of tasks OR 
multiple tasks, at the same time using multi-core infrastructure of CPU, 
by assigning one core to each task or sub-task.

Parallelism requires hardware with multiple processing units, essentially. 
In single core CPU, you may get concurrency but NOT parallelism.

#Differences between the concurrency vs. parallelism : 

Concurrency is when two tasks can start, run, and complete in overlapping time periods. 
Parallelism is when tasks literally run at the same time, eg. on a multi-core processor.

Concurrency is the composition of independently executing processes, while parallelism is 
the simultaneous execution of (possibly related) computations.

Concurrency is about dealing with lots of things at once. 
Parallelism is about doing lots of things at once.

An application can be concurrent – but not parallel, which means that it processes more than 
one task at the same time, but no two tasks are executing at same time instant.

An application can be parallel – but not concurrent, which means that it processes 
multiple sub-tasks of a task in multi-core CPU at same time.

An application can be neither parallel – nor concurrent, which means that 
it processes all tasks one at a time, sequentially.

An application can be both parallel – and concurrent, which means that it processes 
multiple tasks concurrently in multi-core CPU at same time .
