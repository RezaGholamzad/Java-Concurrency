In java language, as we all know that there are two ways to create threads. 
One using Runnable interface and another by extending Thread class.

#Difference between Runnable vs Thread : 

Implementing Runnable is the preferred way to do it. Here, you’re not really specializing 
or modifying the thread’s behavior. You’re just giving the thread something to run. 
That means composition is the better way to go.

Java only supports single inheritance, so you can only extend one class.

Instantiating an interface gives a cleaner separation between your 
code, and the implementation of threads.

Implementing Runnable makes your class more flexible. If you extend Thread then 
the action you’re doing is always going to be in a thread. However, if you 
implement Runnable it doesn’t have to be. You can run it in a thread, or pass 
it to some kind of executor service, or just pass it around as a task within 
a single threaded application.