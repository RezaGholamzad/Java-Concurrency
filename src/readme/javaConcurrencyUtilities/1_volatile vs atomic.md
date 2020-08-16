#Volatile : 

Volatile variables are those variable which are not optimized by the compiler 
while parsing the code. Volatile indicates that this variable value may change every time. 
A volatile variable used when a program is interfacing with hardware or directly with 
the operating system. It means the volatile variable not stored in the cache. 
If we try to access a volatile variable twice, it will be accessed twice from 
the hardware memory, not the cache. In this way, volatile variable avoids optimization.

#Volatile without synchronization : 

Let us take a look at a simple example. We write a program that checks if the printer is 
running or not. In a simple one-threaded program, we declare a boolean printer_running 
variable and see if its still true:

boolean printer_running = true;
 
while(printer_running) {
    //do something
}

In a multi-threaded system this code may not work correctly. If some other thread accesses 
the variable and changes it to false, the thread running this code block may not 
know about the change immediately. This may result in the loop being execute more 
than necessary and may result in data integrity problems, and it is a very hard error 
to find and fix. A solution to this problem may be to declare the printer_running 
variable as volatile. This will make all the changes to the variable by one thread 
be visible to all other threads at once: 

volatile boolean printer_running = true;
 
while(printer_running) {
    //do something
}

This is an example of volatile variable without synchronization. This means that 
the variable can still be accessed by other threads while the code performs changes to it.

#Volatile with synchronization : 

In order for the code to be bullet-proof we need to synchronize the block performing 
operations on volatile variables:

class Foo {
  private volatile boolean printer_running = true;
  public boolean getStatus() {
    if (printer_running) {
      synchronized(this) {
        while(printer_running) {
            //do something
        }
      }
    }
  return printer_running;
}

This code is an example of a double-checked locking or volatile variable with synchronization. 
This code ensures that no other thread gets to execute the while() block while at 
the same time all other threads get to know new updated values of the volatile variable.

#Atomic variables: 

Atomic variables are variables that use a low-level CPU operations. It provides non-blocking 
variable change by multiple threads. The compound operations with such variables performed 
automatically without the use of synchronization. The memory effect is the same as volatile 
variable. The most important advantage of an atomic variable is that it avoids deadlocks.

#Volatile Vs Atomic : 

The effect of the volatile keyword is approximately that each individual read or write 
operation on that variable is atomic.

Notably, however, an operation that requires more than one read/write -- such as i++, 
which is equivalent to i = i + 1, which does one read and one write -- is not atomic, 
since another thread may write to i between the read, and write.

The Atomic classes, like AtomicInteger and AtomicReference, provide a wider 
variety of operations atomically, specifically including increment for AtomicInteger.

#Atomic Boolean : 

Atomic boolean variables are boolean variables that can be updated atomically. 
These variables don’t cause deadlocks and at the same time perform synchronized between threads. 
Atomic booleans are a good choice when a program operates with different threads that can 
access the boolean value simultaneously: 

boolean initialized = false;
 
if (!initialized) {
   initialize();
   initialized = true;
}

This code is not thread safe. If a thread changes the value of initialized Boolean, 
i.e is initialized = true, the whole program gets affected. Atomic Boolean can 
rescue us from this situation:

AtomicBoolean atomicInitialized = new AtomicBoolean ();
 
if (atomicInitialized.compareAndSet(false, true)) {
    initialize();
}

Compare and set is a function defined in Java, which compare 2 values and then swap them. 
The atomic initialized bool variable check the value. If it is true, then it is turned 
false and immediately returned to the memory so that result remains same for all variables.

#Atomic reference : 

Atomic reference is another techniques used in Java to do thread safe operations on the reference. 
The main purpose of atomic referencing is also synchronization: 

AtomicReference<Object> cache = new AtomicReference<Object>();
Object cachedValue = new Object();
cache.set(cachedValue);
Object cachedValueToUpdate = cache.get();
//... do some work to transform cachedValueToUpdate into a new version
Object newValue = someFunctionOfOld(cachedValueToUpdate);
boolean success = cache.compareAndSet(cachedValue,cachedValueToUpdate);

As you can see, that due to atomic referencing, the synchronizations done 
without using a synchronized keyword. It saves resources and 
synchronization is a costly process.

#Atomic Integer :

The AtomicInteger class protects an underlying int value by providing methods that 
perform atomic operations on the value.

AtomicInteger is also provided in Java which provides and integer variable which 
can be read and write properly. It can be accessed by many threads concurrently. 
It also provides a compare and swap instruction support.

private volatile int counter;
 
public int getNextUniqueIndex() {
    return counter++;
}

In this example, multiple threads can change the counter variable and data integrity affected. 
Below is the example of using Atomic Integer:

private AtomicInteger counter;
 
public int getNextUniqueIndex() {
    return counter.getAndIncrement();
}

The beauty of atomic int is that it is tread safe, and it cannot violate the thread safety 
because it achieves same synchronization as in atomic boolean, hence get the correct 
result even multiple threads access it at the same time.

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

#Conclusion : 

As discussed above, the primary use of AtomicInteger is when we are in multi-threaded 
context, and we need to perform atomic operations on an int value without 
using synchronized keyword.

Using the AtomicInteger is equally faster and more readable than performing the same 
using synchronization.