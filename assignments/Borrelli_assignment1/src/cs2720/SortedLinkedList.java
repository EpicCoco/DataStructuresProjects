package cs2720;

import cs2720.ItemType;
import cs2720.NodeType;

/**
 *
 */
public class SortedLinkedList {

    private NodeType head;
    private NodeType currentPos;

    /**
     * Default constructor. Sets the head to a new node,
     * resets the currentPos iterator, and clears the head
     * node.
     */
    public SortedLinkedList() {
        head = new NodeType();
        currentPos = head;
        head.info = new ItemType(0);
    } //SortedLinkedList

    /**
     * Does a loop over the list to get the length.
     * Uses the fact that the last item would point
     * to null to end the count.
     * @param: none
     * @return: int the length of the SortedLinkedList
     */
    public int getLength() {
        NodeType pos = head;
        int count = 0;
        while(pos.next != null) {
            count++;
            pos = pos.next;
        } //while
        return count;
    } //getLength

    /**
     * Inserts an ItemType item into the list, sorted.
     * @param: ItemType item to be inserted
     * @return: none
     */
    public void insertItem(ItemType item) {
        NodeType node = new NodeType();
        node.info = item;
        //uses looseSearch to know where to put the item
        int index = looseSearch(item);
        if (index != 0) {
            //if the item goes anywhere but the start
            node.next = getAtIndex(index);
            getAtIndex(index - 1).next = node;
        } else {
            //if the item goes in the start
            //(reorganize the head node)
            node.info = head.info;
            head.info = item;
            node.next = head.next;
            head.next = node;
        } //if
    } //insertItem

    /**
     * Deletes an item from the list. Adds in necessary
     * connections between nodes to make up for lost node.
     * @param: ItemType item to be deleted (searches for it)
     * @return: none
     */
    public void deleteItem(ItemType item) {
        //test for empty list
        if (getLength() == 0) {
            System.out.println("You cannot delete from an empty list.");
            return;
        } //if
        int itemAt = searchItem(item);
        //test for if item is there
        if (itemAt == -1) {
            System.out.println("Item not found.");
            return;
        } //if
        NodeType pos = searchNode(itemAt);
        if (itemAt == getLength()) { //if item at end
            searchNode(itemAt - 1).next = null;
        } else if (itemAt == 0) { //if item at start
            head = head.next;
        } else { //if item anywhere else
            searchNode(itemAt - 1).next = searchNode(itemAt + 1);
        } //if
    } //deleteItem

    /**
     * Private helper method to search for a NodeType at
     * a specific index. Useful for returning NodeType
     * object.
     * @param: int place (the index)
     * @return: NodeType node found at the index
     */
    private NodeType searchNode(int place) {
        NodeType pos = head;
        for (int i = 0; i < place; i++) {
            pos = pos.next;
        } //for
        return pos;
    } //searchNode

    /**
     * Searches for a speific ItemType item in the list,
     * and returns the index where it's found. returns
     * -1 if not found at all.
     * @param: ItemType item to search for (compareTo)
     * @return: returns index where the item is found (or
     * -1 if not found)
     */
    public int searchItem(ItemType item) {
        NodeType pos = head;
        for (int i = 0; i < this.getLength(); i++) {
            if (pos.info.compareTo(item) == 0) {
                return i;
            } //if
            pos = pos.next;
        } //for
        return -1;
    } //searchItem

    /**
     * Gets the next ItemType item in the list according
     * to the currentPos iterator.
     * If it gets to the end of the list, it loops back
     * over to the beginning.
     * @param: none
     * @return: returns the next item in the list
     */
    public ItemType getNextItem() {
        ItemType temp = currentPos.info;
        currentPos = currentPos.next;
        if (currentPos.next == null) {
            currentPos = head;
        } //if
        return temp;
    } //getNextItem

    /**
     * Resets where the currentPos pointer points to
     * in the list for interation.
     * Sets it to head.
     * @param: none
     * @return: none
     */
    public void resetList() {
        currentPos = head;
    } //resetList

    /**
     * Merges two lists (base list and parameter list).
     * Doesn't allow duplicates.
     * @param: one of the lists to be merged
     * @return: returns the merged list
     */
    public SortedLinkedList mergeList(SortedLinkedList otherList) {
        SortedLinkedList toReturn = new SortedLinkedList();
        //adds all the data in the first list to the new one
        for (int i = 0; i < getLength(); i++) {
            toReturn.insertItem(this.getAtIndex(i).info);
        } //for
        //adds non-repeats from the second list to the new one
        for (int i = 0; i < otherList.getLength(); i++) {
            if (searchItem(otherList.getAtIndex(i).info) == -1) {
                toReturn.insertItem(otherList.getAtIndex(i).info);
            } //if
        } //for
        return toReturn;
    } //mergeList

    /**
     * Deletes alternate notes within the base list.
     * @param: none
     * @return: none
     */
    public void deleteAlternateNodes() {
        NodeType pos = head;
        int length = getLength();
        for (int i = 0; i < length / 2; i++) {
            deleteItem(pos.next.info);
            pos = pos.next;
        } //for
    } //deleteAlternateNodes

    /**
     * Finds the intersection of the base list and the
     * parameter list, and returns it in it's own list.
     * @param: SortedLinkedList to be intersected with
     * the base list.
     * @return: returns a new SortedLinkedList with the
     * intersection of the two lists
     */
    public SortedLinkedList intersection(SortedLinkedList otherList) {
        SortedLinkedList toReturn = new SortedLinkedList();
        for (int i = 0; i < this.getLength(); i++) {
            ItemType item = this.getAtIndex(i).info;
            if (item.compareTo(otherList.getAtIndex(otherList.searchItem(item)).info) == 0) {
                toReturn.insertItem(item);
            } //if
        } //for
        return toReturn;
    } //intersection

    /**
     * Gets the NodeType object at a specific int index
     * in the SortedLinkedList list.
     * Visibility set to package private for internal use.
     * @param: int index to search at
     * @return: NodeType the node at the index
     */
    NodeType getAtIndex(int index) {
        if (index > this.getLength()) {
            return null;
        } //if
        NodeType pos = head;
        for (int i = 0; i < index; i++) {
            pos = pos.next;
        } //for
        return pos;
    } //getAtIndex

    /**
     * Performs a loose search (ie stops when the number
     * gets higher than the parameter, rather than needing
     * them to be equal) on the SortedLinkedList
     * @param: ItemType item to search for
     * @return: int index where the ItemType is loosely
     * found
     */
    private int looseSearch(ItemType item) {
        NodeType pos = head;
        for (int i = 0; i < this.getLength(); i++) {
            if (pos.info.compareTo(item) == 1) {
                return i;
            } //if
            pos = pos.next;
        } //for
        return this.getLength();
    } //looseSearch

    /**
     * ToString method for printing. Formats the string
     * with spaces in between data values in the list.
     * @return: returns formatted string with data in it
     */
    public String toString() {
        String toReturn = "";
        NodeType pos = head;
        for (int i = 0; i < getLength(); i++) {
            toReturn += pos.info.getValue() + " ";
            pos = pos.next;
        } //for
        return toReturn;
    } //toString

} //SortedLinkedList
