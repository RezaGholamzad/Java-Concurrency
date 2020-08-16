Synchronization is the process which keeps all concurrent threads in execution to be in sync.

Synchronization avoids memory consistence errors caused due to inconsistent view of shared memory.

#Object level lock : 
Object level lock is mechanism when we want to synchronize a non-static method or 
non-static code block such that only one thread will be able to execute the code 
block on given instance of the class. This should always be done to make 
instance level data thread safe.

#Class level lock : 
Class level lock prevents multiple threads to enter in synchronized block in any of 
all available instances of the class on runtime. This means if in runtime there 
are 100 instances of DemoClass, then only one thread will be able to execute 
demoMethod() in any one of instance at a time, and all other instances 
will be locked for other threads.

Class level locking should always be done to make static data thread safe. 
As we know that static keyword associate data of methods to class level, 
so use locking at static fields or methods to make it on class level.

#Object level lock vs class level lock : 

Synchronization in Java guarantees that no two threads can execute a synchronized method, 
which requires same lock, simultaneously or concurrently.

the synchronized keyword can be used only with methods and code blocks. 
These methods or blocks can be static or non-static both.

When ever a thread enters into Java synchronized method or block it acquires a lock 
and whenever it leaves synchronized method or block it releases the lock. 
Lock released even if thread leaves synchronized method after completion 
or due to any Error or Exception.

Java synchronized keyword is re-entrant in nature it means if a synchronized method calls 
another synchronized method which requires same lock the then current thread which is 
holding lock can enter into that method without acquiring lock.

Java synchronization will throw NullPointerException if object used in synchronized 
block is null. For example, in above code sample if lock initialized as null, 
the “synchronized (lock)” will throw NullPointerException.

Synchronized methods in Java put a performance cost on your application. 
So use synchronization when it is absolutely required. Also, consider using synchronized 
code blocks for synchronizing only critical section of your code.

It’s possible that both static synchronized and non static synchronized method 
can run simultaneously or concurrently because they lock on the different object.

According to the Java language specification you cannot use the synchronized keyword 
with the constructor. It is illegal and result in compilation error.

Do not synchronize on non final reference object field on synchronized block in Java. 
because reference of non final field may change any time and then different 
thread might synchronizing on different objects i.e. no synchronization at all.

Do not use String literals because they might be referenced else where in 
the application and can cause deadlock. String objects created with new keyword 
can be used safely, But as the best practice, create a new private scoped Object 
instance OR lock on the shared variable itself which we want to protect.