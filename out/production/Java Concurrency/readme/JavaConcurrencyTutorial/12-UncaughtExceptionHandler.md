Java applications have two kinds of exceptions – checked exceptions and unchecked exceptions. 
Checked exceptions must be specified in the throws' clause of a method or caught inside them. 
Unchecked exceptions don’t have to be specified or caught.

When a checked exception thrown inside the run() method of a Thread object, 
we have to catch and handle it accordingly, because the run() method doesn’t accept 
a throws' clause, But when an unchecked exception thrown inside the run() method of 
a Thread object, the default behavior is to write the stack trace in the console 
(or log it inside error log file) and exit the program.

Fortunately, Java provides us with a mechanism to catch and treat the unchecked exceptions 
thrown in a Thread instance to avoid the program crashing. 
This can be done using UncaughtExceptionHandler.

