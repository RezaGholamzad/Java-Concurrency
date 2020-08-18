The factory design pattern is one of the most used design patterns in the java. 
It is one of creational patterns and can be used to develop an object 
in demand of one or several classes. With this factory, 
we centralize the creation of objects.some advantages e.g : 

It’s easy to change the class of the objects created, or the way we create these objects.

It’s easy to limit the creation of objects for limited resources. 
For example, we can only have N objects of a type.

It’s easy to generate statistical data about the creation of the objects.

In java, we usually create threads using two ways i.e. extending thread class 
and implementing runnable interface. Java also provides an interface, 
the ThreadFactory interface, to create your own Thread object factory.

Various classes, like ThreadPoolExecutor, use constructors which accept 
ThreadFactory as argument. This factory used when the executor creates 
a new thread. Using ThreadFactory you can customize the threads created 
by executor so that they have proper thread names, priority or even they 
can be set to daemon as well.