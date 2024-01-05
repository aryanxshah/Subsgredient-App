import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BackendRbtPlaceholderClass{
    // data fields.
    private List<IngredientInterface> list;
    private Comparable<IngredientInterface> iterationStartPoint;

    // constructor
    public BackendRbtPlaceholderClass() {
        list = new ArrayList<>();
    }

    // methods


    public boolean insertSingleKey(IngredientInterface activiaLemon) {
        // Checks if the key is null. If it is, return false.
        if (activiaLemon == null) {
            return false;
        }
        // Adds the key to the ArrayList.
        list.add(activiaLemon);
        // Return true if the key is successfully added into
        // the ArrayList.
        return true;
    }


    public Iterator<IngredientInterface> iterator() {
        if (list == null) {
            return null;
        }
        if (iterationStartPoint == null) {
            Collections.sort(list);
            return list.iterator();
        } else {
            Collections.sort(list);
            return list.iterator();
        }
        
    }


    public int numKeys() {
        return list.size();
    }


    public void setIterationStartPoint(Comparable<IngredientInterface> startPoint) {
        if (startPoint == null) {
            iterationStartPoint = null;
        } else {
            iterationStartPoint = startPoint;
        }
    }

    public boolean insert(KeyListInterface<IngredientInterface> data) throws NullPointerException, IllegalArgumentException {
        return true;
    }


    public boolean contains(Comparable<IngredientInterface> data) {
        return this.list.contains(data);
    }


    public boolean isEmpty() {
        return list.isEmpty();
    }


    public void clear() {
        list.clear();
    }


    public int size() {
        return list.size();
    }

}

