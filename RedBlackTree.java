// --== CS400 Fall 2023 File Header Information ==--
// Name: Tanya Santhosh
// Email: tsanthosh@wisc.edu
// Group: A36
// TA: Connor Bailey
// Lecturer: Gary Dahl
// Notes to Grader:

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RedBlackTree < T extends Comparable<T>> extends BinarySearchTree<T>{
    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;
        public RBTNode(T data) { super(data); }
        public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
        public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
        public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
    }

    protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode) {

        if(newNode!=null){
            newNode.blackHeight = 0;
            if(newNode.up!=null) {
                RBTNode parent = newNode.getUp();
                RBTNode grandParent = newNode.getUp().getUp();
                if (parent.blackHeight == 0) {
                    if (parent != null && grandParent != null) {
                        //NULL CHECKER FOR PARENT ON THE LEFT
//            if (grandParent.getDownLeft() != null) {
                        if (grandParent.getDownLeft() == parent) {
                            //PARENT IS ON THE LEFT
                            RBTNode<T> aunt = grandParent.getDownRight();
                            // BLACK AUNT
                            if (aunt == null || aunt.blackHeight == 1) {
                                int grandParentColour = grandParent.blackHeight;
                                int childColour = newNode.blackHeight;
                                int parentColour = parent.blackHeight;

                                if (newNode.isRightChild()) {
                                    rotate(newNode, parent);
                                    rotate(newNode, grandParent);
                                    newNode.blackHeight = grandParentColour;
                                    grandParent.blackHeight = childColour;
                                }
                                if (!newNode.isRightChild()) {
                                    rotate(parent, grandParent);
                                    parent.blackHeight = grandParentColour;
                                    grandParent.blackHeight = parentColour;
                                }
                            } else if (aunt.blackHeight == 0) {
                                // red aunt
                                parent.blackHeight = 1;
                                grandParent.blackHeight = 0;
                                aunt.blackHeight = 1;
                            }
                        } else if (grandParent.getDownRight() == parent) {
                            // Parent is on the right
                            RBTNode<T> aunt = grandParent.getDownLeft();
                            int grandParentColour = grandParent.blackHeight;
                            int childColour = newNode.blackHeight;
                            int parentColour = parent.blackHeight;


                            //BLACK AUNT
                            if (aunt == null || aunt.blackHeight == 1) {

                                if (!newNode.isRightChild()) {
                                    rotate(newNode, parent);
                                    rotate(newNode, grandParent);
                                    newNode.blackHeight = grandParentColour;
                                    grandParent.blackHeight = childColour;
                                } else if (newNode.isRightChild()) {
                                    rotate(parent, grandParent);
                                    parent.getDownLeft().blackHeight = 0;
                                    parent.blackHeight = 1;
                                }
                            } else if (aunt.blackHeight == 0) {
                                // red aunt

                                parent.blackHeight = 1;
                                grandParent.blackHeight = 0;
                                aunt.blackHeight = 1;
                            } else {
                                throw new IllegalStateException("Parent is not a child of its grandparent");
                            }

                        }
                    }
                }
                if(grandParent!= (RBTNode<T>) root ){
                    enforceRBTreePropertiesAfterInsert(grandParent);
                }
            }

        }
    }

    @Override
    public boolean insert(T data) throws NullPointerException {

        if (data == null)
            throw new NullPointerException("Cannot insert data value null into the tree.");
        RBTNode<T> newNode  = new RBTNode<T>(data);
        boolean returnData = this.insertHelper(newNode);
        this.enforceRBTreePropertiesAfterInsert(newNode);

        RBTNode<T> root = (RBTNode<T>) this.root;
        root.blackHeight = 1;
        return returnData;
    }



    /**
     * testing for leaf node insert without rotating or color swap
     *
     */
    @Test
    public void testSingleNode() {
        // Creating a new empty tree.
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

        // Inserting values into RBT
        rbt.insert(13);
        rbt.insert(6);
        rbt.insert(18);
        rbt.insert(2);
        rbt.insert(12);

        String expected = "[ 13, 6, 18, 2, 12 ]";
        String actual = rbt.toLevelOrderString();
        RBTNode <Integer> node12 = (RBTNode <Integer>)rbt.findNode(12);

        Assertions.assertEquals(actual, expected);

        if (!expected.equals(actual) && node12.blackHeight != 0){
            Assertions.fail("Test failed: error with leaf node insertion where blackheight = 0");
        }
    }

    /**
     * testing for leaf node insertion when aunt node is black
     *
     */
    @Test
    public void testNodeWithBlackAunt(){
        // Creating a new empty tree.
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

        // Inserting values into RBT
        rbt.insert(14);
        rbt.insert(7);
        rbt.insert(18);
        rbt.insert(1);
        rbt.insert(11);
        rbt.insert(23);
        rbt.insert(20);

        String expected = "[ 14, 7, 20, 1, 11, 18, 23 ]";
        String actual = rbt.toLevelOrderString();

        RBTNode <Integer> node20 = (RBTNode <Integer>)rbt.findNode(20);
        RBTNode <Integer> node23 = (RBTNode <Integer>)rbt.findNode(23);
        RBTNode <Integer> node18 = (RBTNode <Integer>)rbt.findNode(18);
        RBTNode <Integer> node14 = (RBTNode <Integer>)rbt.findNode(14);

        Assertions.assertEquals(expected, actual);

        if (node20.blackHeight != 1 &&
                node23.blackHeight != 0 && node18.blackHeight != 0 && node20.up != node14 &&
                node20.getDownLeft() != node18 && node20.getDownRight() != node23)
        {
            Assertions.fail("Test failed: error with leaf node insertion where blackheight = 1");
        }

    }

    /**
     * testing for leaf node insertion when aunt node is red
     *
     */
    @Test
    public void testInsertNodeWithRedAunt() {
        // Creating a new empty tree.
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

        // Inserting values into RBT
        rbt.insert(14);
        rbt.insert(7);
        rbt.insert(18);
        rbt.insert(1);
        rbt.insert(11);
        rbt.insert(23);
        rbt.insert(20);
        rbt.insert(29);

        String expected = "[ 14, 7, 20, 1, 11, 18, 23, 29 ]";
        String actual = rbt.toLevelOrderString();

        RBTNode <Integer> node20 = (RBTNode <Integer>)rbt.findNode(20);
        RBTNode <Integer> node23 = (RBTNode <Integer>)rbt.findNode(23);
        RBTNode <Integer> node18 = (RBTNode <Integer>)rbt.findNode(18);
        RBTNode <Integer> node29 = (RBTNode <Integer>)rbt.findNode(29);

        Assertions.assertEquals(actual, expected);

        if (node20.blackHeight != 0 &&
                node23.blackHeight != 1 && node18.blackHeight != 1 && node29.blackHeight != 0)
        {
            Assertions.fail("Test failed: error with leaf node insertion where blackheight = 2");
        }

    }



}


