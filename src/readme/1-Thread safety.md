At the heart of any reasonable definition of thread safety is the concept of correctness.
Correctness means that a class conforms to its specification.

You will agree that a good class specification will have all information about
a class’s state at any given time, and it’s post condition if
some operation performed on it.

A class is thread-safe if it behaves correctly when accessed from multiple threads,
regardless of the scheduling or interleaving of the execution of those threads
by the runtime environment,and with no additional synchronization or other coordination
on the part of the calling code.

A good example of thread safe class is java servlets which have no fields and references,
no fields from other classes etc. They are stateless.

The transient state for a particular computation exists solely in local variables that are
stored on the thread’s stack and are accessible only to the executing thread.
One thread accessing a StatelessFactorizer cannot influence the result of another
thread accessing the same StatelessFactorizer; because the two threads do not share state,
it is as if they were accessing different instances. Since the actions of a thread accessing
a stateless object cannot affect the correctness of operations in other threads,
stateless objects are thread-safe.