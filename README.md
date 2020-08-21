#concurrent vs parallel vs asynchronous :

I am with a scenario that happened in my life that explains concurrency vs parallelism vs asynchronous programming.

During my childhood there were days when my mom woke up late, which delayed the process of getting me, and my sister ready to school. She started speeding things up. At a given time :

+She was preparing my lunch box

+She was feeding me some breakfast

+She was reviewing my school dairy and signing it wherever necessary .

If you see this pattern, she is working on multiple things at once by RAPID CONTEXT SWITCHING to achieve her target.

This property of single core(my mom) working on multiple things at once by rapidly switching the context is called concurrency.

**************************************

#So what is parallelism?

At this point by seeing how hard my mom is working, my grand mom came into picture.

She started doing all the tasks for my sister just like my mom is doing it for me.

If you see this pattern over here, each core (mom core and grand mom core) is working concurrently on their core but on an overall basis they are two cores running parallel.

Concurrency and parallelism are two related but distinct concepts. Concurrency means, essentially, that task A and task B both need to happen independently of each other, and A starts running, and then B starts before A finish.

There are various different ways of accomplishing concurrency. One of them is parallelism--having multiple CPUs working on the different tasks at the same time, But that's not the only way. Another is by task switching, which works like this: Task A works up to a certain point, then the CPU working on it stops and switches over to task B, works on it for a while, and then switches back to task A. If the time slices are small enough, it may appear to the user that both things run in parallel, even though they're actually being processed in serial by a multitasking CPU.

**************************************

#What Is Multithreading Programming?

Multithreading refers to the concurrent/parallel execution of more than one sequential set (thread) of instructions.

On a single processor, multithreading gives the illusion of running in parallel. In reality, the processor is switching by using a scheduling algorithm. Or, it’s switching based on a combination of external inputs (interrupts) and how the threads have been prioritized.

On multiple processor cores, threads are truly parallel. Individual microprocessors work together to achieve the result more efficiently. There are multiple parallel, concurrent tasks happening at once.

**************************************

#What Is Asynchronous Programming?

In a concurrent execution, context switching happen rapidly and is not on an event basis. Asynchronous programming is mostly event driven.

In async, you write code as tasks which are executed concurrently. You never know which tasks will be started first — it depends on the executing context, whether you run tasks in parallel or do a bit of one task then progress to another.

An asynchronous model allows multiple things to happen at the same time. When your program calls a long-running function, it doesn’t block the execution flow, and your program continues to run. When the function finishes, the program knows and gets access to the result (if there’s a need for that).

One approach to asynchronous programming is to make functions that perform a slow action and take an extra argument, a callback function. The action started, and when it finishes, the callback function called with the result.

**************************************

#Asynchronous vs Multithreading :

From the definitions we just provided, we can see that multithreading programming is all about concurrent execution of different functions. Async programming is about non-blocking execution between functions, and we can apply async with single-threaded or maltreated programming.

So, multithreading is one form of asynchronous programming.

Let’s take a simple analogy; you have a friend, and you decided to make dinner together.

Async is when you say to your friend, “You go to the store and buy pasta. Let me know when you get back, to make dinner together. Meanwhile, I’ll prepare sauce and drinks.”

Threading is, “You boil the water. I’ll heat the tomato sauce. While the water is boiling, ask me that I’ll put the pasta in. When the sauce is hot, you can add cheese. When both are done, I’ll sit down, and you serve dinner. Then we eat.”. In the threading analogy, we can see the sequence of “When, Do” events, which represent the sequential set of instructions per each person (thread).

From that analogy, we can conclude that Multithreading is about workers, Asynchronous is about tasks.

**************************************

#Which One To Use?

Choosing between the two programming models depends mainly on performance.

Given all possible combinations between sync/async and single/multi-threading, which model should perform better?

In a nutshell, for large scale applications with a lot of I/O operations and different computations, using asynchronous multithreading programming flow, will utilize the computation resources, and take care of non-blocking functions. This is the programming model of any OS!

With more power, comes more responsibility! So if we decided to implement this model, we have to take care of different issues like race condition, deadlocks, shared resources, and callbacks events.
