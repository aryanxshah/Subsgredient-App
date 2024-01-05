// --== CS400 Fall 2023 File Header Information ==--
// Name: Aryan Shah
// Email: atshah2@wisc.edu
// Group: A36
// TA: Conner Bailey
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

//Class that models a RedBlackTree

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    /**
     * Node class that stores color of each node as well as the parent nodes, children nodes
     *
     */

    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;
        public RBTNode(T data) { super(data); }
        public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
        public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
        public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
    }


    /**
     * Method that resolves red property violations that occur after inserting a new node
     * into the RedBlackTree
     *
     * @param node new red node
     */
    protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> node) {
        RBTNode<T> nodeAdded = node;
        while (nodeAdded != root && nodeAdded.getUp().blackHeight == 0) {

            RBTNode<T> parent = nodeAdded.getUp();
            RBTNode<T> grandparent = parent.getUp() != null ? parent.getUp() : null;

            if (grandparent == null) {
                break;
            }
            RBTNode<T> uncle = (RBTNode<T>) (parent == grandparent.down[0] ? grandparent.down[1] : grandparent.down[0]);

            if (uncle != null && uncle.blackHeight == 0) {

                parent.blackHeight = 1;

                grandparent.blackHeight = 0;

                uncle.blackHeight = 1;
                nodeAdded.blackHeight = 0;
            }
            else {
                if (!parent.isRightChild()) {
                    if (nodeAdded == parent.down[1]) {
                        rotate(nodeAdded, parent);
                        int temp = nodeAdded.blackHeight;
                        nodeAdded.blackHeight = grandparent.blackHeight;
                        grandparent.blackHeight=temp;
                    }
                    if (grandparent.down[0] != null){
                        rotate(grandparent.down[0], grandparent);
                        int temp = parent.blackHeight;
                        parent.blackHeight = grandparent.blackHeight;
                        grandparent.blackHeight = temp;
                    }
                }
                else {
                    if (nodeAdded == parent.down[0]) {
                        rotate(nodeAdded, parent);
                        int temp = nodeAdded.blackHeight;
                        nodeAdded.blackHeight = grandparent.blackHeight;
                        grandparent.blackHeight = temp;
                        nodeAdded = nodeAdded.getUp().getUp();
                    }
                    if (grandparent.down[1] != null){
                        rotate(grandparent.down[1], grandparent);
                        int temp = parent.blackHeight;
                        parent.blackHeight = grandparent.blackHeight;
                        grandparent.blackHeight = temp;
                    }
                }
                break;
            }
        }
        if (root != null && root instanceof RBTNode) {
            RBTNode<T> rbRoot = (RBTNode<T>) root;
            rbRoot.blackHeight = 1;
        }
    }


    @Override

    public boolean insert(T data) throws NullPointerException {
        if (data == null){
            throw new NullPointerException("Cannot insert data value null into the tree.");
        }

        RBTNode<T> newNode = new RBTNode<T>(data);
        boolean inserted = this.insertHelper(newNode);
        if(inserted) {
            enforceRBTreePropertiesAfterInsert(newNode);
            RBTNode<T> nodeAdded = newNode;
            while (root!=nodeAdded) {
                nodeAdded = nodeAdded.getUp();
            }
            nodeAdded.blackHeight= 1;

            //Sets root node to black
            //(RBTNode<T>)this.root.heightBlack = 1;
        }
        return inserted;
    }


    //Tests for wheather or not RedBlackTree correctly adds new nodes to empty tree

    @Test

    public void testInsertToEmptyTree() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        assertTrue(tree.isEmpty());
        //Checks insertions into an empty tree
        assertTrue(tree.insert(15));
        assertFalse(tree.isEmpty());

        assertTrue(tree.contains(15));
    }

    /*
     * Tests for wheather or not black nodes in RedBlackTree have the correct height after insertion
     */

    @Test

    public void testMultipleNodesInserted() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        //Insertion of multiple nodes into RedBlackTree
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(20));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(25));
        assertTrue(((RBTNode<T>)tree.root).blackHeight == 1); // Black height of root node must be 1

        // Check the black height of specific nodes in the Red-Black Tree
        assertTrue(((RBTNode<T>) tree.root).blackHeight == 1); // Root's black height should be 1
        assertTrue(((RBTNode<T>) tree.root).getDownLeft().blackHeight == 1); // Node with value 10 should have a black height of 1
        assertTrue(((RBTNode<T>) tree.root).getDownRight().blackHeight == 1); // Node with value 20 should have a black height of 1
        assertTrue(((RBTNode<T>) tree.root).getDownLeft().getDownLeft().blackHeight == 0); // Node with value 5 should have a black height of 0 (red node)
        assertTrue(((RBTNode<T>) tree.root).getDownRight().getDownRight().blackHeight == 0); // Node with value 25 should have a black height of 0 (red node)
        // Additional assertions (commented out) can be added as needed to check other nodes

    }

    /**
     * Tests for whether or not black nodes in RedBlackTree have the correct height in this particular
     * scenario for where color reorder is needed.
     */

    @Test

    public void testMultipleNodesInserted2() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        assertTrue(tree.isEmpty());

        //Insertion of multiple nodes in order to create a black node violation
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(20));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(25));
        assertTrue(tree.insert(8));
        assertTrue(((RBTNode<T>)tree.root).blackHeight == 1); // Black height of root node must be 1

        assertTrue(((RBTNode<T>)tree.root).getDownLeft().blackHeight == 0);
        assertTrue(((RBTNode<T>)tree.root).getDownRight().blackHeight == 1);
        assertTrue(((RBTNode<T>)tree.root).getDownLeft().getDownRight().blackHeight == 1);
        assertTrue(((RBTNode<T>)tree.root).getDownRight().getDownRight().blackHeight == 0);
    }
}
