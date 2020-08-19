Java ThreadLocal is used to create thread local variables. We know that all threads of an Object 
share its variables, so the variable is not thread safe. We can use synchronization for thread 
safety but if we want to avoid synchronization, we can use ThreadLocal variables.

Every thread has its own ThreadLocal variable, and they can use its get() and set() methods 
to get the default value or change its value local to Thread.

