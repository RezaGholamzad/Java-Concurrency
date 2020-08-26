we will learn to use such a new feature ThreadPoolExecutor in combination with BlockingQueue. 
We will see the best practices for ThreadPoolExecutor and BlockingQueue classes 
to use in our application.

#BlockingQueue : 

If you remember solving the producer-consumer problem, before JDK 5, consumer had to 
wait until producer put something in resource queue. This problem can be easily 
solved using new BlockingQueue.

BlockingQueue is like another Queue implementations with additional capabilities. 
Any attempt, to retrieve something out of it, can be seen safe as it will not return 
empty-handed. Consumer thread will automatically wait until BlockingQueue is not populated 
with some data. Once it fills, the thread will consume the resource.

