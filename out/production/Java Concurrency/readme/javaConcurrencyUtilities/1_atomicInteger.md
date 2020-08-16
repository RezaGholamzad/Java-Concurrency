The AtomicInteger class protects an underlying int value by providing methods that 
perform atomic operations on the value. It shall not be used as a replacement 
for an Integer class.

#When to use AtomicInteger : 

As an atomic counter which is being used by multiple threads concurrently.

In compare-and-swap operations to implement non-blocking algorithms.

#AtomicInteger as atomic counter : 

addAndGet() – Atomically adds the given value to the current value and returns new 
value after the addition.

getAndAdd() – Atomically adds the given value to the current value and returns old value.

incrementAndGet() – Atomically increments the current value by 1 and returns 
new value after the increment. It is equivalent to ++i operation.

getAndIncrement() – Atomically increment the current value and returns old value. 
It is equivalent to i++ operation.

decrementAndGet() – Atomically decrements the current value by 1 and returns 
new value after the decrement. It is equivalent to i- – operation.

getAndDecrement() – Atomically decrements the current value and returns old value. 
It is equivalent to – -i operation.

#Compare and swap operations : 

A compare and swap operation compares the contents of a memory location to a given value and, 
only if they are the same, modifies the contents of that memory location to a given new value. 
This is done as a single atomic operation.

The atomicity guarantees that the new value calculated based on up-to-date information; 
if the value had been updated by another thread in the meantime, write would fail.

To support compare and swap operations, this class provides a method which atomically 
sets the value to the given updated value if the current value == the expected value.

boolean compareAndSet(int expect, int update)

We can see many real time uses of compareAndSet() method in Java concurrent collection 
classes such as ConcurrentHashMap.