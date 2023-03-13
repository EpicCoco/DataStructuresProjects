package cs2720;

/**
 * NodeType class for use in the BinarySearchTree.
 * Has a type that extends Comparable for comparison.
 * Meant to behave like a struct.
 */
public class NodeType<T extends Comparable<T>> {
    public T info;
    public NodeType<T> left;
    public NodeType<T> right;

} //NodeType
