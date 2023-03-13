Codey Borrelli 811691794
crb70853@uga.edu

This project has a src directory with project source code and
a bin directory with compiled code.

In order to compile and run the program, type
    ./build.sh
(note that this command will compile and then run the program using input.txt as input for the program)
OR
type the following javac commands in order:
    javac -d bin src/cs2720/NodeType.java
    javac -d bin -cp bin src/cs2720/DoublyLinkedList.java
    javac -d bin -cp bin src/cs2720/DoublyLinkedListDriver.java
followed by
    java -cp bin cs2720.DoublyLinkedListDriver int-input.txt
where "int-input.txt" can be replaced by another input text file containing ints, doubles, or Strings.

When running the program, the user must specify the type they wish to use (and it must match the types
in the input file).


deleteSubsection:
Pseudo cude:

void deleteSubsection(T lower bound, T upper bound) {
    Make pos node and find length //time complexity of 1
    for(each item in the unmodified list) { ------------------------------------------------
        if(the current node is within the bounds) {                                         |
            delete that item //time complexity of n (since delete uses search function)     | time complexity of n^2
        } //if                                                                              |
        set the pos node to the next in the list //time complexity of 1                     |
    } //for --------------------------------------------------------------------------------
} //deleteSubsection

The blocks have a time complexity of 1, n^2. Therefore the overall O complexity of the method is O(n^2)


reverseList:
Pesudo code:

void reverseList() {
    Make pos node and make a temporary head node, set to the last element in the list //time complexity of n (since uses getAtIndex method)
    for(each item in the list) { ----------------------------------------------------------------------
        set the pos back node to the next and iterate the pos node to the next //time complexity of 1  | //time complexity of n
    } //for -------------------------------------------------------------------------------------------
    reset pos to head and make a new node prev to be used to store the previous node //time complexity of 1
    for(each item in the list) { -------------------------------------------------------------------------------------
        set the pos next value to the prev one, set the prev to the pos node, and iterate pos to the next //comp of 1 | //time complexity of n
    } //for ----------------------------------------------------------------------------------------------------------
    set the old head to the temporary one, and change the class variable of reversed to the opposite //time complexity of 1
} //reverseList

The blocks have a time complexity of n, n, 1, n, 1. Therefore the overall O complexity of the method is O(n)


swapAlternate:
Pseudo code:

void swapAlternate() {
    Make pos node for iterating //time complexity of 1
    for (each pair of items in the list) { --------------------------
        //all items in the for loop have time complexity of 1        |
        if(not first item) {                                         |
            set the next back to the pos back                        |
        } //if                                                       |
        set the pos back to the pos next                             |
        if (the list isn't even or the iterator is at the end) {     |
            pos next next's back is set to pos                       |
            pos next is set to pos next's next                       |
        } //if                                                       | //time complexity of n
        set pos's back's next to pos                                 |
        if (not first item) {                                        |
            pos back's back's next is set to pos's back              |
        } //if                                                       |
        if (the first item) {                                        |
            head is set to pos's back                                |
        } //if                                                       |
        iterate pos to the next group                                |
    } //for ---------------------------------------------------------
} //swapAlternate

The blocks have a time complexity of 1, n. Therefore, the overall O complexity of the method is O(n)
