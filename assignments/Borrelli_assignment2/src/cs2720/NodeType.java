package cs2720;

/**
 * NodeType class for use in the DoublyLinkedList.
 * Has a type that extends Comparable for comparison.
 * Meant to behave like a struct.
 */
public class NodeType<T extends Comparable<T>> {
    public T info;
    public NodeType<T> next;
    public NodeType<T> back;
} //NodeType
