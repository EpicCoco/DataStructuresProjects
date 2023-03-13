package cs2720;

/**
 * DoublyLinkedList class. Can take in an Object
 * type that extends Comparable. Provides utilities
 * for the list.
 */
public class DoublyLinkedList<T extends Comparable<T>> {

    private NodeType<T> head;
    private int length;
    private boolean reversed;

    /**
     * Zero parameter constructor.
     * Assigns head value, sets length to 0, and sets
     * reversed to false.
     */
    public DoublyLinkedList() {
        head = new NodeType<T>();
        length = 0;
        reversed = false;
    } //DoublyLinkedList

    /**
     * Inserts an item into the list, sorted.
     * Prevents duplicates.
     * Note: accounts for when the list is reversed,
     * but not when the alternates are swapped.
     * @param: T item to be inserted into the list
     */
    public void insertItem(T item) {
        NodeType<T> node = new NodeType<T>();
        node.info = item;
        //uses looseSearch to know where to put the item
        int index = looseSearch(item);
        if (index != 0) {
            //first test if it already exists
            if (searchItem(item) != -1) {
                System.out.println("Item already exists");
                return;
            } //if
            //if the item goes anywhere but the start
            node.next = getAtIndex(index);
            getAtIndex(index - 1).next = node;
            node.back = getAtIndex(index - 1);
            if (index != this.getLength()) {
                getAtIndex(index + 1).back = node;
            } //if
        } else {
            //if the item goes in the start
            //(reorganize the head node)
            node.info = head.info;
            head.info = item;
            node.back = head;
            //special case - if list isn't empty,
            //needs to also reassign the back
            if (this.getLength() > 0) {
                head.next.back = node;
            } //if
            node.next = head.next;
            head.next = node;
        } //if
        length++;
    } //insertItem

    /**
     * Deletes an item from the list.
     * Won't delete if the item doesn't exist in the
     * list or if the list is empty.
     * @param: T item to be deleted
     */
    public void deleteItem(T item) {
        //test for empty list
        if (this.getLength() == 0) {
            System.out.println("You cannot delete from an empty list");
            return;
        } //if
        int itemAt = searchItem(item);
        //test for if item is there
        if (itemAt == -1) {
            System.out.println("The item is not present in the list");
            return;
        } //if
        if (itemAt == getLength() - 1) { //if item at end
            getAtIndex(itemAt - 1).next = null;
        } else if (itemAt == 0) { //if item at start
            head = head.next;
            head.back = null;
        } else { //if item anywhere else
            getAtIndex(itemAt + 1).back = getAtIndex(itemAt - 1);
            getAtIndex(itemAt - 1).next = getAtIndex(itemAt + 1);
        } //if
        length--;
    } //deleteItem

    /**
     * Getter method for the length of the list.
     * @return: returns an int representing the length
     */
    public int getLength() {
        return length;
    } //getLength

    /**
     * Prints the info stored in the list in a single line.
     * Demonstrates that the next values are properly assigned.
     * Does not modify the list.
     */
    public void print() {
        NodeType pos = head;
        for (int i = 0; i < this.getLength(); i++) {
            System.out.print(pos.info + " ");
            pos = pos.next;
        } //for
        System.out.println();
    } //print

    /**
     * Prints the list but in reverse, used to demonstrate
     * that the list properly assigns back values to the
     * nodes in the list.
     * Prints the info on one line.
     * Does not modify the list.
     */
    public void printReverse() {
        NodeType<T> pos = getAtIndex(this.getLength() - 1);
        for (int i = this.getLength(); i > 0; i--) {
            //printNode(i);
            System.out.print(pos.info + " ");
            pos = pos.back;
        } //for
        System.out.println();
    } //printReverse

    /**
     * Reverses the order of the list.
     * Reassigns the next and back values of the nodes in the
     * list so it is reversed.
     * Does not touch the .info of the nodes.
     */
    public void reverseList() {
        NodeType<T> pos = head;
        NodeType<T> newHead = getAtIndex(this.getLength() - 1);
        for (int i = 0; i < this.getLength(); i++) {
            pos.back = pos.next;
            pos = pos.next;
        } //for
        pos = head;
        NodeType<T> prev = null;
        for (int i = 0; i < this.getLength(); i++) {
            pos.next = prev;
            prev = pos;
            pos = pos.back;
        } //for
        head = newHead;
        reversed = !reversed;
    } //reverseList

    /**
     * Deletes a subsection of the list between two
     * bounds (inclusive).
     * Uses the compareTo method to compare the items.
     * @param: lb the lower bound of the subsection
     * @param: ub the upper bound of the subsection
     */
    public void deleteSubsection(T lb, T ub) {
        NodeType<T> pos = head;
        int startLength = this.getLength();
        for (int i = 0; i < startLength; i++) {
            //big if statement checking if the item is
            //between or equal to the bounds
            if ((pos.info.compareTo(ub) > 0 &&
            pos.info.compareTo(lb) < 0) ||
            pos.info.compareTo(ub) == 0 ||
            pos.info.compareTo(lb) == 0) {
                deleteItem(pos.info);
            } //if
            pos = pos.next;
        } //for
    } //deleteSubsection

    /**
     * Swaps every two nodes with eachother.
     * Works on the list as if it were even in length
     * (only works on pairs of items).
     * Reassigns the nodes' next and back values, but does
     * not touch their .info
     */
    public void swapAlternate() {
        NodeType<T> pos = head;
        //treat the list as if it were even
        for (int i = 0; i < (length % 2 == 0 ? length : length - 1); i += 2) {
            if (i > 0) {
                pos.next.back = pos.back;
            } //if
            pos.back = pos.next;
            //catches end exceptions
            if (length % 2 == 1 || i < length - 2) {
                pos.next.next.back = pos;
                pos.next = pos.next.next;
            } //if
            pos.back.next = pos;
            if (i > 0) {
                pos.back.back.next = pos.back;
            } //if
            if (i == 0) {
                head = pos.back;
            } //if
            pos = pos.next;
        } //for
    } //swapAlternate

    /**
     * Private helper method for loosely searching for an
     * item in the list. Will return the index of where the item
     * should go if it were to be inserted into the list.
     * Accounts for if the list is reversed or not.
     * @param: item to loosely search for.
     * @return: int for where the item should go in the list
     */
    private int looseSearch(T item) {
        NodeType<T> pos = head;
        for (int i = 0; i < this.getLength(); i++) {
            //if reversed, it should be less than.
            //if not reveresed, it should be greater than
            if ((pos.info.compareTo(item) > 0 && !reversed) ||
            (pos.info.compareTo(item) < 0 && reversed)) {
                return i;
            } //if
            pos = pos.next;
        } //for
        //else, return the end of the list
        return this.getLength();
    } //looseSearch

    /**
     * Private helper method for searching exactly where an item
     * is in the list.
     * @param: item to search for
     * @return: Where in the list the item is (-1 if not found)
     */
    private int searchItem(T item) {
        NodeType<T> pos = head;
        for (int i = 0; i < this.getLength(); i++) {
            //check for working with strings!
            if (pos.info.compareTo(item) == 0) {
                return i;
            } //if
            pos = pos.next;
        } //for
        return -1;
    } //searchItem

    /**
     * Helper method for finding what node is at a certain
     * index.
     * @param: int index to get the node at
     * @return: returns the node found at the input index
     * Returns null if the index is out of bounds.
     */
    NodeType<T> getAtIndex(int index) {
        if (index > this.getLength()) {
            return null;
        } //if
        NodeType<T> pos = head;
        for (int i = 0; i < index; i++) {
            pos = pos.next;
        } //for
        return pos;
    } //getAtIndex
} //DoublyLinkedList
