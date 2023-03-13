Codey Borrelli 811691794
crb70853@uga.edu

This project has a src directory with project source code and
a bin directory with compiled code.

In order to compile and run the program (where it reads input from a file), type
    ./build.sh
(note that this command will compile and then run the program using reverse.txt as input for the program
AS WELL AS runs the program using file IO for data input)
OR
type the following javac commands in order:
    javac -d bin src/cs2720/Sort.java
    javac -d bin -cp bin src/cs2720/SortDriver.java
followed by
    java -cp bin cs2720.SortDriver reverse.txt
where "reverse.txt" can be replaced by another input text file containing ints to be sorted.

In order to compile and run the program with the ability to specify the size of the array of random ints,
type the following javac commands in order:
    javac -d bin src/cs2720/Sort.java
    javac -d bin -cp bin src/cs2720/SortDriver2.java
followed by
    java -cp bin cs2720.SortDriver2
in order to run the program.
Note: during runtime, the program will first ask for the number of elements in the array. The user should
type an int representing the array size, inclusive. The program will then generate an array of ints from
zero to the length of the array, and randomly swap them around (essentially no two ints will repeat).
The program will then run similar to SortDriver.java, in that it will ask for the sorting algorithm
type and print the comparison count, then quit.



Credit to code in Sort.java taken from other sources:

For methods selectionSort and minIndex:
Author: Sachin Meena
meena@uga.edu
Taken from lecture slides, CH10_Sorting

For methods merge and mergeSort:
Author: Rajat Mishra
Source: https://www.geeksforgeeks.org/merge-sort/

For methods heapSort and heapify:
Author: Unknown
Source: https://www.geeksforgeeks.org/java-program-for-heap-sort/

For methods quickSort and partition:
Author: Ayush Choudhary
Source: https://www.geeksforgeeks.org/quick-sort/
