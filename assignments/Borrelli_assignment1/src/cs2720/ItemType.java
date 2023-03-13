package cs2720;

/**
 * ItemType class to store within NodeType objects.
 * Has methods to access, manipulate, and compare the
 * data stored (in this case, it's integers stored)
 */
public class ItemType {

    private int value;

    //parametized constructor for value
    public ItemType(int value) {
        this.value = value;
    } //ItemType

    //compareTo method to compare ItemType objects
    public int compareTo(ItemType item) {
        if (this.value == item.getValue()) {
            return 0;
        } else if (this.value < item.getValue()) {
            return -1;
        } //if
        return 1;
    } //compareTo

    //getter method for value stored (int)
    public int getValue() {
        return value;
    } //getValue

} //ItemType
