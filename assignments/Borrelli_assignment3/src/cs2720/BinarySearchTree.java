package cs2720;

/**
 * A data type representing a Binary Seach Tree (BST).
 * Uses generics to allow multiple different data types
 * to be used in the BST.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private NodeType<T> root;
    private int length;

    /**
     * Constructs an empty BST with a length of 0.
     */
    public BinarySearchTree() {
        length = 0;
    } //BinarySearchTree

    /**
     * Inserts an item into the list based on it's
     * key, item type T. Will reassign root if
     * necessary. Uses a recursive helper method to
     * insert an item in the tree.
     * @param: T key to be inserted into the list
     */
    public void insert (T key) {
        NodeType<T> newNode = new NodeType<T>();
        newNode.info = key;
        //reassign root
        if (root == null) {
            root = newNode;
            return;
        } //if
        //search for if exists in list
        if (retrieve(key)) {
            //item already in list
            System.out.println("The item already exists in the tree.");
            return;
        } //if
        //recursive insert
        insertHelp(root, newNode);
        length++;
    } //insert

    /**
     * Private recursive helper method for inserting
     * an item into the BST.
     * @param: NodeType place for keeping track of
     * place in recursion tree. Set as root when
     * calling the method externally.
     * @param: NodeType key to be inserted into the
     * list.
     */
    private void insertHelp (NodeType<T> place, NodeType<T> key) {
        //key less than place (go left)
        if (key.info.compareTo(place.info) < 0) {
            if (place.left != null) {
                insertHelp (place.left, key);
            } else {
                place.left = key;
            } //if
            return;
        } //if
        //key greater than place (go right)
        if (key.info.compareTo(place.info) > 0) {
            if (place.right != null) {
                insertHelp (place.right, key);
            } else {
                place.right = key;
            } //if
            return;
        } //if
    } //insertHelp

    /**
     * Delete method, which makes sure the item is
     * in the list and that the nodes are properly
     * rearranged after deletion. Has special cases
     * for if the root is being deleted.
     * @param: T key to be deleted from the list
     * @return: returns int, 1 if deletion successful,
     * 0 if deletion failed (item didn't exist in
     * the list).
     */
    public int delete (T key) {
        //special case for root
        if (root.info.compareTo(key) == 0) {
            length--;
            //no children of root
            if (root.left == null && root.right == null) {
                root = null;
                return 1;
            } //if
            //child on left of root
            if (root.left != null && root.right == null) {
                root = root.left;
                return 1;
            } //if
            //child on right of root
            if (root.left == null && root.right != null) {
                root = root.right;
                return 1;
            } //if
            if (root.left != null && root.right != null) {
                //reassign from rightmost child on left
                NodeType<T> temp = root.left;
                while (temp.right != null) {
                    temp = temp.right;
                } //while
                T tempInfo = temp.info;
                delete(temp.info);
                root.info = tempInfo;
                return 1;
            } //if
        } //if
        NodeType<T> place = search(root, key);
        if (place == null) {
            return 0;
        } //if
        NodeType<T> parent = findParent(root, key);
        boolean onLeft = (parent.left != null && parent.left.info.compareTo(key) == 0);
        length--;
        //case zero children
        if (place.left == null && place.right == null) {
            if (onLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            } //if
            return 1;
        } //if
        //case left child
        if (place.left != null && place.right == null) {
            if (onLeft) {
                parent.left = place.left;
            } else {
                parent.right = place.left;
            } //if
            return 1;
        } //if
        //case right child
        if (place.left == null && place.right != null) {
            if (onLeft) {
                parent.left = place.right;
            } else {
                parent.right = place.right;
            } //if
            return 1;
        } //if
        //case two children
        if (place.left != null && place.right != null) {
            NodeType<T> temp = place.left;
            while (temp.right != null) {
                temp = temp.right;
            } //while
            T tempInfo = temp.info;
            delete(temp.info);
            place.info = tempInfo;
            return 1;
        } //if
        return 0;
    } //delete

    /**
     * Private recursive helper method to find the
     * parent of a node in the BST. Assumes the
     * key in parameter is in the list.
     * @param: NodeType place for keeping track of
     * place in recursion tree. Set as root when
     * calling the method externally.
     * @param: T key to find parent of
     * @return: returns parent of key node
     */
    private NodeType<T> findParent(NodeType<T> place, T key) {
        //case root
        if (key.compareTo(root.info) == 0) {
            return root;
        } //if
        if (key.compareTo(place.info) < 0) {
            if (place.left != null && key.compareTo(place.left.info) == 0) {
                return place;
            } else {
                return findParent(place.left, key);
            } //if
        } else {
            if (place.right != null && key.compareTo(place.right.info) == 0) {
                return place;
            } else {
                return findParent(place.right, key);
            } //if
        } //if
    } //findParent

    /**
     * Private recursive helper method for searching
     * for a node in the list. Returns null if node
     * not found.
     * @param: NodeType place for keeping track of
     * place in recursion tree. Set as root when
     * calling the method externally.
     * @param: T key to be searched for
     * @return: returns node if found, null if not
     */
    private NodeType<T> search(NodeType<T> place, T key) {
        if (key.compareTo(place.info) == 0 ) {
            return place;
        } //if
        if (key.compareTo(place.info) < 0) {
            if (place.left != null) {
                return search(place.left, key);
            } else {
                return null;
            } //if
        } else {
            if (place.right != null) {
                return search(place.right, key);
            } else {
                return null;
            } //if
        } //if
    } //search

    /**
     * Returns true if the parameter key is in the
     * list, false if it isn't. Calls upon helper
     * search recursive method.
     * @param: T key to be "retrieved"
     * @return: returns boolean according to if
     * key found in BST or not.
     */
    public boolean retrieve (T key) {
        if (search(root, key) != null) {
            return true;
        } //if
        return false;
    } //retrieve

    /**
     * Prints the nodes in the BST in order.
     * Calls upon recursive printInOrder method.
     */
    public void inOrder() {
        //if root null, no printing nodes
        if (root == null) {
            System.out.println();
            return;
        } //if
        printInOrder(root);
        System.out.println();
    } //inOrder

    /**
     * Private recursive helper method for printing
     * the nodes in order of the sort.
     * @param: NodeType place for keeping track of
     * place in recursion tree. Set as root when
     * calling the method externally.
     */
    private void printInOrder(NodeType<T> place) {
        //print left first
        if (place.left != null) {
            printInOrder(place.left);
        } //if
        //then print self
        System.out.print(place.info + " ");
        //finally print right
        if(place.right != null) {
            printInOrder(place.right);
        } //if
    } //printInOrder

    /**
     * Prints the nodes that are a single parent, i.e.
     * have one child. Calls upon singleParent recursive
     * helper method.
     */
    public void getSingleParent() {
        if (root == null) {
            System.out.println();
            return;
        } //if
        singleParent(root);
        System.out.println();
    } //getSingleParent

    /**
     * Private recursive helper method for seeing
     * if a parent has only one child. If so, print it.
     * @param: NodeType place for keeping track of
     * place in recursion tree. Set as root when
     * calling the method externally.
     */
    private void singleParent(NodeType<T> place) {
        if (place.left != null) {
            singleParent(place.left);
        } //if
        if ((place.left != null && place.right == null) ||
        (place.left == null && place.right != null)) {
            System.out.print(place.info + " ");
        } //if
        if (place.right != null) {
            singleParent(place.right);
        } //if
    } //singleParent

    /**
     * Returns the number of leaf nodes (i.e.
     * nodes with no children) that are found
     * in the BST. Calls upon leafNodes helper
     * recursive method.
     * @return: returns number of leaf nodes
     * found in the BST.
     */
    public int getNumLeafNodes() {
        return leafNodes(root);
    } //getNumLeafNodes

    /**
     * Private recursive helper method for finding
     * the number of leaf nodes in the BST.
     * @param: NodeType place for keeping track of
     * place in recursion tree. Set as root when
     * calling the method externally.
     */
    private int leafNodes(NodeType<T> place) {
        int total = 0;
        if (place.left != null) {
            total += leafNodes(place.left);
        } //if
        if (place.right != null) {
            total += leafNodes(place.right);
        } //if
        if (place.left == null && place.right == null) {
            return 1;
        } //if
        return total;
    } //leafNodes

    /**
     * Prints out the cousins of a given node.
     * cousins share the same grandparent, but not
     * the same parent node. Therefore, a single
     * node can only have a maximum of 2 cousins.
     * Specifically calls upon getLevelOfNode and
     * printNodeChildren private helper methods.
     * @param: T key to find cousins of in the BST
     */
    public void getCousins(T key) {
        System.out.print(key + " cousins: ");
        int level = getLevelOfNode(root, key, 1);
        //level 1 or 2 wouldn't have a grandparent
        //therefore no cousins. so quit
        if (level < 3) {
            System.out.println();
            return;
        } //if
        //cousins need same grandparent but
        //different parent
        NodeType<T> parent = findParent(root, key);
        NodeType<T> grandparent = findParent(root, parent.info);
        if (key.compareTo(grandparent.info) < 0) {
            //go right
            if (grandparent.right != null) {
                printNodeChildren(grandparent.right);
            } //if
        } else {
            //go left
            if (grandparent.left != null) {
                printNodeChildren(grandparent.left);
            } //if
        } //if
        System.out.println();
    } //getCousins

    /**
     * Private helper method for printing the
     * existing children of a node. Prints left
     * to right, or least to greatest.
     * Used for the getCousins method
     * @param: NodeType node to have it's children
     * printed.
     */
    private void printNodeChildren(NodeType<T> node) {
        //print left
        if (node.left != null) {
            System.out.print(node.left.info + " ");
        } //if
        //print right
        if (node.right != null) {
            System.out.print(node.right.info + " ");
        } //if
    } //printNode

    /**
     * Private recursive helper method that gets
     * the level, or depth, of a node.
     * @param: NodeType place for keeping track of
     * place in recursion tree.
     * @param: T key to find the level of
     * @param: int level to keep track of the current
     * level, or depth in the BST
     * @return: returns the level of the node
     */
    private int getLevelOfNode(NodeType<T> place, T key, int level) {
        if (key.compareTo(place.info) == 0 ) {
            return level;
        } //if
        if (key.compareTo(place.info) < 0) {
            if (place.left != null) {
                return getLevelOfNode(place.left, key, level + 1);
            } //if
        } else {
            if (place.right != null) {
                return getLevelOfNode(place.right, key, level + 1);
            } //if
        } //if
        return 0;
    } //getLevelOfNode

} //BinarySearchTree
