DelayQueue class is an unbounded blocking queue of delayed elements, 
in which an element can only be taken when its delay has expired. DelayQueue class is part of 
java.util.concurrent package.

#What is a delayed element?

An element will be considered delayed when it implements java.util.concurrent.Delayed interface 
and it’s getDelay() method return a zero or negative value which indicate 
that the delay has already elapsed.

To make things more clear, we can consider that each element stores it activation date/time. 
As soon as, this timestamp reaches, element is ready to be picked up from queue. 
The getDelay() method returns the time until the activation of the element.

Note that an implementation of Delayed interface must define a compareTo() method that 
provides an ordering consistent with its getDelay() method.

compareTo(Delayed o) method does not return the actual timestamp, generally. 
It return a value less than zero if the object that is executing the method has a delay 
smaller than the object passed as a parameter – otherwise a positive value greater than zero. 
It will return zero if both the objects have the same delay.

#What is DelayQueue?

A DelayQueue is an unbounded blocking queue of Delayed elements.
When a consumer of element wants to take an element from the queue, 
it can take only when the delay for that particular element has expired.

DelayQueue is a specialized PriorityQueue that orders elements based on their delay time.

The head of the queue is the element whose delay expired furthest in the past.

If there is no element whose delay has expired yet, there is no head element in the queue 
and poll() will return null.

Even though unexpired elements cannot be removed using take() or poll(), 
they are otherwise treated as normal elements in the queue i.e. size() method returns 
the count of both expired and unexpired elements.

This queue does not permit null elements because their delay cannot be determined.
