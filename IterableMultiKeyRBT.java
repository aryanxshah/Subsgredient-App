import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>> implements IterableMultiKeySortedCollectionInterface<T> {
    private Comparable<Object> iterationStartPoint; // Field to store the iteration start point
    private int numKeys; // Field to store the number of keys
    /**
     * Inserts value into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree.
     * @param key object to insert
     * @return true if obj was inserted
     */
    @Override
    public boolean insertSingleKey(T key) {
        KeyList<T> keyList = new KeyList(key);

        Node<KeyListInterface<T>> existingNode = this.findNode(keyList);
        if (existingNode == null) {
            this.insert(keyList);
            ++this.numKeys;
            return true;
        } else {
            KeyList<T> list = (KeyList<T>) existingNode.data;
            list.addKey(key);
            ++this.numKeys;
            return false;
        }
    }

    /**
     * @return the number of values in the tree.
     */
    public int numKeys(){
        return numKeys; // Return the number of keys stored in the field
    }


    /**
     * Returns an iterator that does an in-order iteration over the tree.
     */
    @Override
    public Iterator<T> iterator(){
        Stack<Node<KeyListInterface<T>>> stack = new Stack<>();
        Node<KeyListInterface<T>> toor = root;

        if (iterationStartPoint == null) {
            while (toor != null) {
                stack.push(toor);
                toor = toor.down[0];
            }
        } else {
            while (toor != null) {
                stack.push(toor);
                T next = toor.data.iterator().next();
                int diff = iterationStartPoint.compareTo(next);
                if (diff == 0)
                    break;
                else if (diff >= 0)
                    toor = toor.down[1];
                else
                    toor = toor.down[0];
            }
        }

        class IteratorHolder
        {
            public Node<KeyListInterface<T>> currentNode = null;
            public Iterator<T> keyListIter = null;
        }

        final IteratorHolder holder = new IteratorHolder();

        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return stack.isEmpty() == false || (holder.keyListIter != null && holder.keyListIter.hasNext());
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("No next node");

                if(holder.keyListIter == null || !holder.keyListIter.hasNext())
                {
                    holder.currentNode = stack.pop();
                    holder.keyListIter = holder.currentNode.data.iterator();
                }

                T key = holder.keyListIter.next();

                return key;
            }
        };
    }



    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is remembered
     * until it is reset. Passing in null disables the starting point.
     * @param startPoint the start point to set for iterations
     */
    public void setIterationStartPoint(Comparable startPoint){
        this.iterationStartPoint = startPoint; // Set the iteration start point
    }
    @Override
    public void clear() {
        super.clear(); // Call the clear method of the parent class

        // After clearing the tree, set the number of keys to zero
        numKeys = 0;
    }




    protected Stack<Node> getStartStack(){
        Stack<Node> stack = new Stack<>();

        Node node = this.root;
        if (iterationStartPoint == null){
            while (node != null){
                stack.push(node);
                node = node.down[0];
            }

        }
        else {
            while (node != null) {
                int cmp = iterationStartPoint.compareTo(((KeyList)node.data).iterator().next());
                if (cmp < 0) {
                    stack.push(node);
                    node = node.down[0];
                } else if (cmp > 0) {
                    node = node.down[1];
                }
                else {
                    // Found the start point node, add it to the stack and break
                    stack.push(node);
                    break;
                }
            }
        }
        return stack;
    }

    @Test
    public void testInsertSingleKey() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Insert elements
        tree.insertSingleKey(5);
        tree.insertSingleKey(3);
        tree.insertSingleKey(7);
        tree.insertSingleKey(3);
        tree.insertSingleKey(6);

        // Check total elements
        assertEquals(5, tree.numKeys());
    }

    @Test
    public void testIterator() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Insert values into the tree
        tree.insertSingleKey(5);
        tree.insertSingleKey(3);
        tree.insertSingleKey(8);
        tree.insertSingleKey(1);
        tree.insertSingleKey(6);

        // Create an iterator and iterate over the tree
        Iterator<Integer> iterator = tree.iterator();
        int[] expectedOrder = {1, 3, 5, 6, 8};
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(expectedOrder[i], iterator.next().intValue());
            i++;
        }
    }

    /**
     * Tester for the numKeys() method. This tests inserts multiple new and duplicate keys into
     * the tree and checks if the number of keys returned by the method is correct. Test fails if
     * the wrong number is returned.
     */
    @Test
    public void testNumKeys() {
        // Creating a new empty extended RBT:
        IterableMultiKeyRBT <Integer> testRBTnumKeys = new IterableMultiKeyRBT<>();

        // Creating 10 Integer objects for inserting into the RBT:
        Integer num20 = 20;
        Integer num20dup1 = 20;
        Integer num5 = 5;
        Integer num5dup1 = 5;
        Integer num25 = 25;
        Integer num25dup1 = 25;
        Integer num25dup2 = 25;
        Integer num30 = 30;

        // Inserting values into the RBT (Both original and duplicate keys have been added, which the
        // method must count as separate)
        testRBTnumKeys.insertSingleKey(num20);
        testRBTnumKeys.insertSingleKey(num20dup1);
        testRBTnumKeys.insertSingleKey(num5);
        testRBTnumKeys.insertSingleKey(num5dup1);
        testRBTnumKeys.insertSingleKey(num25);
        testRBTnumKeys.insertSingleKey(num25dup1);
        testRBTnumKeys.insertSingleKey(num25dup2);
        testRBTnumKeys.insertSingleKey(num30);

        if (testRBTnumKeys.numKeys() != 8) {
            System.out.println("Expected: " + 8);
            System.out.println("Actual: " + testRBTnumKeys.numKeys());
            Assertions.fail("Test failed: The method did not return the right number of keys in the " +
                    "tree.");
        }
    }



}





