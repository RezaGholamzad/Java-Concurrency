Quite obvious, binary semaphore can have a value either 0 or 1. It means binary semaphore protect 
the access to a SINGLE shared resource, so the internal counter of the semaphore can only take 
the values 1 or 0.

So whenever you have a requirement for protecting the access to a SINGLE resource accessed 
by multiple threads, you can use Binary Semaphore.