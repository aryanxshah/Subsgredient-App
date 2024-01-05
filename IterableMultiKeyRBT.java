import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class IterableMultiKeyRBT<KeyType extends Comparable<KeyType>> extends RedBlackTree<KeyListInterface<KeyType>>
        implements IterableMultiKeySortedCollectionInterface<KeyType> {

    // New field to store iteration starting point
    private Comparable<KeyType> iterationStartPoint;
    // New field that tracks # of keys
    private int numKeys;




    /**
     * Inserts value into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree.
     *<
     * @param key object to insert
     * @return true if new node was inserted, and return false if they key was added to an already existing node
     */
    @Override
    public boolean insertSingleKey(KeyType key){

        //creating key list
        KeyListInterface<KeyType> list = new KeyList<>(key);
        //node will not be null if it already exists
        Node<KeyListInterface<KeyType>> dupe = findNode(list);

        if( dupe == null )
        {
            insert(list);
            numKeys++;
            return true;
        }
        dupe.data.addKey(key);
        numKeys++;
        return false;
    }

    /**
     * @return the number of values in the tree.
     */
    @Override
    public int numKeys(){
        return numKeys;
    }

    /**
     * Returns an iterator that does an in-order iteration over the tree.
     */
    @Override
    public Iterator<KeyType> iterator(){
        Stack<Node<KeyListInterface<KeyType>>> stack = new Stack<>();
        Node<KeyListInterface<KeyType>> toor = root;

        if (iterationStartPoint == null) {
            while (toor != null) {
                stack.push(toor);
                toor = toor.down[0];
            }
        } else {
            while (toor != null) {
                stack.push(toor);
                KeyType next = toor.data.iterator().next();
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
            public Node<KeyListInterface<KeyType>> currentNode = null;
            public Iterator<KeyType> keyListIter = null;
        }

        final IteratorHolder holder = new IteratorHolder();

        return new Iterator<KeyType>() {

            @Override
            public boolean hasNext() {
                return stack.isEmpty() == false || (holder.keyListIter != null && holder.keyListIter.hasNext());
            }

            @Override
            public KeyType next() {
                if (!hasNext()) throw new NoSuchElementException("No next node");

                if(holder.keyListIter == null || !holder.keyListIter.hasNext())
                {
                    holder.currentNode = stack.pop();
                    holder.keyListIter = holder.currentNode.data.iterator();
                }

                KeyType key = holder.keyListIter.next();


//                Node<KeyListInterface<KeyType>> rightChild = holder.currentNode.down[1];
//                while (rightChild != null) {
//                    stack.push(rightChild);
//                    rightChild = rightChild.down[0];
//                }

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
    @Override
    public void setIterationStartPoint(Comparable<KeyType> startPoint){
        iterationStartPoint = (KeyType) startPoint;
    }
    @Override
    public void clear() {
        super.clear();
        numKeys = 0; // Reset key count
    }

    /**
     * Tests insertSingleKey to check that insertion values into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree
     */
    @Test
    public void testInsertSingleKey(){
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(5);
        Iterator<Integer> iter = tree.iterator();
        Assertions.assertTrue(iter.hasNext());
        Assertions.assertEquals(5, iter.next());
        Assertions.assertFalse(iter.hasNext());
    }

    /**
     * Tests numKeys to check the number of values in the tree is correct
     */
    @Test
    public void testnumKeys() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(9);
        tree.insertSingleKey(11);
        tree.insertSingleKey(13);

        // Check if numKeys returns the expected value
        int numKeys = tree.numKeys();
        Assertions.assertEquals(3, numKeys); // Change 3 to the expected number of keys
    }


    /**
     * Tests iterator to check that an iterator that does an in-order iteration over the tree.
     */
    @Test
    public void testIterator(){
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(60);
        tree.insertSingleKey(40);
        tree.insertSingleKey(80);
        Iterator<Integer> iter = tree.iterator();
        int last = Integer.MIN_VALUE;
        while (iter.hasNext()) {
            int current = iter.next();
            Assertions.assertTrue(current >= last);
            last = current;
        }

    }

    /**
     * Tests setIterationStartPoint to check the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is remembered
     * until it is reset. Passing in null disables the starting point.
     *
     */
    @Test
    public void testSetIterationStartPoint(){
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(60);
        tree.insertSingleKey(40);
        tree.insertSingleKey(80);

        tree.setIterationStartPoint(50);

        Iterator<Integer> iter = tree.iterator();

        // Should start at 60 since 50 was set as start point
        Assertions.assertTrue(iter.next() == 40, "First key should be 60");

    }

}
