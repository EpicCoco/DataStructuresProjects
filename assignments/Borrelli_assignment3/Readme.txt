Codey Borrelli 811691794
crb70853@uga.edu

This project has a src directory with project source code and
a bin directory with compiled code.

In order to compile and run the program, type
    ./build.sh
(note that this command will compile and then run the program using int-input1.txt as input for the program)
OR
type the following javac commands in order:
    javac -d bin src/cs2720/NodeType.java
    javac -d bin -cp bin src/cs2720/BinarySearchTree.java
    javac -d bin -cp bin src/cs2720/BinarySearchTreeDriver.java
followed by
    java -cp bin cs2720.BinarySearchTreeDriver int-input1.txt
where "int-input1.txt" can be replaced by another input text file containing ints, doubles, or Strings.

When running the program, the user must specify the type they wish to use (and it must match the types
in the input file).


-----------------------------------------------------------------------


getSingleParent pesudo code:
public void getSingleParent() { ---------------------
    if (root is empty) {                             |
        print blank line                             | gets O(logn) from singleParent
    } //if                                           | method (solved with Master
    call singleParent (helper recursive function)    | theorem)
    print blank line                                 |
} //getSingleParent ---------------------------------

singleParent helper method pseudo code:
private void singleParent(Node place) {
    if (node to the left isn't null) { //comparison
        singleParent(node to the left) //recursive call
    } //if
    if (the node has just one child) { //comparison
        print place info //print
    } //if
    if (node to the right isn't null) { //comparison
        singleParent(node to the right) //recursive call
    } //if
} //singleParent


Since only one or the other (left or right) calls are made, and they each divide
the list in half, the recurrence relation used is T(n/2). Print statement has O(1)
T(n) = T(n/2) + 1
a = 1, b = 2, d = 0. a == b^d (1==1)
According to Master Theorem, O(n^0 logn) = O(logn)

getSingleParent big O:
O(logn)


-----------------------------------------------------------------------


getNumLeafNodes pesudo code:
public int getNumLeafNodes() { --
    return leafNodes(root)       | uses leafNodes helper method, solved with Master theorem
} //getNumLeafNodes -------------

leafNodes helper method pseudo code:
private int leafNodes(Node place) {
    int total = 0
    if (node to the left isn't null) {
        total += leafNodes(node to the left)
    } //if
    if (node to the right isn't null) {
        total += leafNodes(node to the right)
    } //if
    if (node to the left isn't null AND node to the right isn't null) {
        return 1
    } //if
    return total
} //leafNodes

Since only one or the other (left or right) calls are made, and they each divide
the list in half, the recurrence relation used is T(n/2). Adding to total variable
has O(1)
T(n) = T(n/2) + 1
a = 1, b = 2, d = 0. a == b^d (1==1)
According to Master Theorem, O(n^0 logn) = O(logn)

getNumLeafNodes big O:
O(logn)


-----------------------------------------------------------------------


getCousins pseudo code:
public void getCousins(key) { -----------------------------------------
    print(formatting) //constant                                       |
    int level = getLevelOfNode(of key) //O(logn)                       |
    if (level is less than 3) { -------                                |
        print new line                 | constant                      |
        end the method                 | O(1)                          |
    } //if ----------------------------                                |
    Node parent = findParent(of key) //O(logn)                         |
    Node grandparent = findParent(of parent) //O(logn)                 | constant plus
    if (key < grandparent) { ------------------------------            | O(logn) x3
        if (node to right of grandparent not null) {       |           |
            printNodeChildren(node right of grandparent)   |           |
        } //if                                             | constant  |
    } else {                                               | O(1)      |
        if (node to left of grandparent not null) {        |           |
           printNodeChildren(node left of grandparent)     |           |
        } //if                                             |           |
    } //if ------------------------------------------------            |
    print new line //constant                                          |
} //getCousins --------------------------------------------------------

printNodeChildren method has bigO(1) - is just comparisons and print statements

getLevelOfNode helper method pseudo code:
private int getLevelOfNode(Node place, key, int level) {
    if (key equals place info) { -
        return level              | constant (base case)
    } //if -----------------------
    if (key greater than place info) {
        if (node to left isn't null) {
            return getLevelOfNode(node to the left, key, level + 1)
        } //if
    } else {
        if (node to the right isn't null) {
            return getLevelOfNode(node to the right, key ,level + 1)
        } //if
    } //if
    return 0
//getLevelOfNode

Since only one or the other (left or right) calls are made, and they each divide
the list in half, the recurrence relation used is T(n/2).
T(n) = 1 + T(n/2)
a = 1, b = 2, d = 0. a == b^d (1==1)
According to Master Theorem, O(n^0 logn) = O(logn)

findParent helper method pseudo code:
private Node findParent(Node place, key) {
    if (key equals root info) { --
        return root               | constant (base case)
    } //if -----------------------
    if (key less than place info) {
        if (node left not null and key equals node to left) {
            return place
        } else {
            return findParent(node to the left, key)
        } //if
    } else {
        if (node right not null and key equals node to right) {
            return place
        } else {
            return findParent(node to the right, key)
        } //if
    } //if
} //findParent

Since only one or the other (left or right) calls are made, and they each divide
the list in half, the recurrence relation used is T(n/2). Base case O(1)
T(n) = 1 + T(n/2)
a = 1, b = 2, d = 0. a == b^d (1==1)
According to Master Theorem, O(n^0 logn) = O(logn)

getCousins big O:
O(logn)
