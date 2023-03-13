Codey Borrelli
crb70853@uga.edu

In order to compile and run the program, type
    ./build.sh
(note that this command will compile and then run the program using input.txt as input for the program)
OR
type the following javac commands in order:
    javac -d bin src/cs2720/ItemType.java
    javac -d bin -cp bin src/cs2720/NodeType.java
    javac -d bin -cp bin src/cs2720/SortedLinkedList.java
    javac -d bin -cp bin src/cs2720/LinkedListDriver.java
followed by
    java -cp bin cs2720.LinkedListDriver input.txt
where "input.txt" can be replaced by another input text file.


Merge function:
Pseudo code:
SortedLinkedList mergeList(SortedLinkedList otherList) {
    Make a new list to add the merged lists to (called toReturn) //time complexity of 1
    for (each item in list 1) { //time complexity of n---------------------------------
        add the list 1 items to toReturn list //time complexity of n (insert function) | time complexity of n^2
    } //for----------------------------------------------------------------------------
    for(each item in list 2) { //time complexity of n---------------------
        if(search - item i isn't in list 1) { //time complexity of n      |
            add the list 2 item to toReturn list //time complexity of n   | time complexity of n^2
        } //if                                                            |
    } //for---------------------------------------------------------------
    return toReturn list //time complexity of 1
} //mergeList
//The blocks have a time complexity of 1, n^2, n^2, 1. Therefore the overall O complexity of the method is O(n^2)


Intersection function:
Pseudo code:
SortedLinkedList intersection(SortedLinkedList otherList) {
    Make a new list to add the intersected lists to (called toReturn) //time complexity of 1
    for(each item in list 1) { //time complexity of n--------------------------------------------------
        make a new item representing the list 1 info (to be compared to) //time complexity of n        |
        if(item compared to the other list searching for that item are equal) { //time complexity of n | time complexity of n^2
            add that item to toReturn list //time complexity of n                                      |
        } //if                                                                                         |
    } //for--------------------------------------------------------------------------------------------
    return toReturn list //time complexity of 1
} //intersection
The blocks have a time complexity of 1, n^2, 1. Therefore the overall O complexity of the method is O(n^2)
