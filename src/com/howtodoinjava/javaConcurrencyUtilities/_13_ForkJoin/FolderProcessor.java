package com.howtodoinjava.javaConcurrencyUtilities._13_ForkJoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {

    private static final long serialVersionUID = 1L;
    //This attribute will store the full path of the folder this task is going to process.
    private final String path;
    //This attribute will store the name of the extension of the files this task is going to look for.
    private final String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {

        //List to store the names of the files stored in the folder.
        List<String> list = new ArrayList<>();

        /*
            FolderProcessor tasks to store the subtasks that are going to process
            the subfolders stored in the folder
         */
        List<FolderProcessor> tasks = new ArrayList<>();

        //Get the content of the folder.
        File file = new File(path);
        File[] content = file.listFiles();

        /*
            For each element in the folder, if there is a subfolder, create a
            new FolderProcessor object and execute it asynchronously using the fork() method.
         */
        if (content != null){

            for (File item : content) {

                if (item.isDirectory()) {
                    FolderProcessor task = new FolderProcessor(item.getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);
                } else {
                    /*
                        Otherwise, compare the extension of the file with the extension you are looking
                        for using the checkFile() method and, if they are equal, store the full path
                        of the file in the list of strings declared earlier.
                     */
                    if (checkFile(item.getName())) {
                        list.add(item.getAbsolutePath());
                    }
                }
            }
        }

        /*
            If the list of the FolderProcessor subtasks has more than 50 elements,
            write a message to the console to indicate this circumstance:
        */
        if (tasks.size() > 50){
            System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
        }

        //add to the list of files the results returned by the subtasks launched by this task.
        addResultFormTasks(list, tasks);

        //Return the list of strings
        return list;
    }

    /*
        For each task stored in the list of tasks, call the join() method that will wait for 
        its finalization and then will return the result of the task. 
        Add that result to the list of strings using the addAll() method.
     */
    private void addResultFormTasks(List<String> list, List<FolderProcessor> tasks){
        for (FolderProcessor item : tasks){
            list.addAll(item.join());
        }
    }


    /*
        This method compares if the name of a file passed as a parameter ends with the extension
        you are looking for.
     */
    private boolean checkFile(String name){
        return name.endsWith(extension);
    }
}
