package com.howtodoinjava.javaConcurrencyUtilities._11_ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {

    /*
        let’s create 100 threads adding data into list and 100 threads for removing
        data from list. If the list is truly thread-safe and non-blocking, it will
        give you final result almost instantly. Moreover, list size in end will be zero.
     */
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];

        /*
            First, you have executed 100 AddTask tasks to add elements to the list.
            Each one of those tasks inserts 10,000 elements to the list using the add() method.
            This method adds the new elements at the end of the list. When all those tasks have
            finished, you have written in the console the number of elements of the list.
            At this moment, the list has 1,000,000 elements.
         */
        for (int i = 0; i < threads.length; i++) {
            AddTask addTask = new AddTask(list);
            threads[i] = new Thread(addTask);
            threads[i].start();
        }

        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d\n", list.size());

        /*
            Then, you have executed 100 RemoveTask tasks to remove elements from the list.
            Each one of those tasks removes 10,000 elements of the list using the pollFirst()
            and pollLast() methods. The pollFirst() method returns and removes the first element
            of the list and the pollLast() method returns and removes the last element of the list.
            If the list is empty, these methods return a null value. When all those tasks have finished,
            you have written in the console the number of elements of the list. At this moment,
            the list has zero elements.
         */
        for (int i = 0; i < threads.length; i++) {
            RemoveTask removeTask = new RemoveTask(list);
            threads[i] = new Thread(removeTask);
            threads[i].start();
        }

        System.out.printf("Main: %d RemoveTask threads have been launched\n", threads.length);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
            To write the number of elements of the list, you have used the size() method.
            You have to take into account that this method can return a value that is not real,
            especially if you use it when there are threads adding or deleting data in the list.
            The method has to traverse the entire list to count the elements and the contents
            of the list can change for this operation. Only if you use them when there aren’t
            any threads modifying the list, you will have the guarantee that the returned
            result is correct.
         */
        System.out.printf("Main: Size of the List: %d\n", list.size());
    }
}
