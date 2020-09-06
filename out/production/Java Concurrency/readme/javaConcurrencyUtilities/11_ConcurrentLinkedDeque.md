#ConcurrentLinkedDeque – Non-blocking Thread-safe List : 

In java, most used data structure is probably a list. A list has an undetermined number 
of elements, and you can add, read, or remove the element of any position. Additionally, 
concurrent lists allow the various threads to add or remove elements in the list at a time 
without producing any data inconsistency. And non-blocking lists provide operations that, 
if the operation can’t be done immediately, lists throw an exception or return a null value, 
depending on the operation. Java 7 has introduced the ConcurrentLinkedDeque class that 
implements a non-blocking concurrent list and in this tutorial, we will learn to use this class.

ConcurrentLinkedDeque class provides more methods to get elements form the list:

+getFirst() and getLast(): These methods return the first and last element from the list 
respectively. They don’t remove the returned element from the list. If the list is empty, 
these methods throw a NoSuchElementExcpetion exception.

+peek(), peekFirst(), and peekLast(): These methods return the first and the last element of 
the list respectively. They don’t remove the returned element from the list. If the list 
is empty, these methods return a null value.

+remove(), removeFirst(), removeLast(): These methods return the first and the last element of 
the list respectively. They remove the returned element from the list. If the list is empty, 
these methods throw a NoSuchElementException exception.

+A ConcurrentLinkedDeque is an appropriate choice when many threads will share access 
to a common collection.

+Like most other concurrent collection implementations, this class does not permit 
the use of null elements.

+Iterators are weakly consistent, returning elements reflecting the state of the deque 
at some point at or since the creation of the iterator. They do not throw 
ConcurrentModificationException, and may proceed concurrently with other operations.

What that means is weakly consistent iterators? 

1)they may proceed concurrently with other operations

2)they will never throw ConcurrentModificationException

3)they are guaranteed to traverse elements as they existed upon construction exactly once, 
and may (but are not guaranteed to) reflect any modifications after construction.

The third bullet in particular explicitly doesn't make any guarantees about what elements 
are seen by the iteration if modifications occur during iteration.

#Snapshot: 

In this policy, the iterator is associated with the state of the collection from the moment 
when the iterator was created — our snapshot of the collection. Any change made to the initial 
collection creates a fresh version of the underlying data structure. Of course, our snapshot is 
untouched, so it doesn’t reflect any changes made to the collection after the iterator was created.

This is the good old copy-on-write (COW) technique. It completely solves the concurrent 
modification problem, so no ConcurrentModificationException can be thrown. Additionally, 
the iterators do not support element-changing operations. Copy-on-write collections are 
usually too expensive to use, but it might be a good idea to give it a try if traversal 
mutations happen significantly less often. The examples are CopyOnWriteArrayList and 
CopyOnWriteArraySet.

###########################

So you can see that there are a lot of things that can go wrong with iterating over a 
collection while it's being modified. Weakly consistent iteration provides guarantees against 
repeated elements and against a variety of errors or infinite loops that can occur. 
The "weakness" is that they provide few guarantees about exactly which elements are 
observed during iteration.

Finally, note that fail-fast(A fail-fast iterator throws ConcurrentModificationException 
if the collection is modified while iterating, but fail-safe doesn’t.) and weakly consistent 
are particular terms that are defined and used in the Java SE specifications. 
The term "fail-safe" isn't used anywhere in official 
Java documentation. Therefore, I recommend not using "fail-safe" to describe any of 
the Java collections' concurrent-modification policies. Some people think that "fail-safe" 
is the opposite of "fail-fast" and you'll see this occur in various blogs and articles around 
the internet. Frankly, I think this is sloppy writing that should be avoided.

 