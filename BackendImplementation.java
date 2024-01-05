import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class BackendImplementation implements BackendInterface {
    List<Ingredient> ingredients;
    @Override
    public List<IngredientInterface> readData(String dataFilePath, String delimiter) throws FileNotFoundException {
        List<IngredientInterface> ingredients = new ArrayList<>(); // Initialize the list to store ingredients

        try (Scanner scnr = new Scanner(new File(dataFilePath))) {
            scnr.nextLine(); // Skip the header line

            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                String[] sections = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                String category = sections[0];
                String name = sections[1];

                String[] parts = sections[3].split(" ");
                int calories = Integer.parseInt(parts[0]);

                String calPer100 = sections[2];
                String kJPer100 = sections[4];

                Ingredient ingredient = new Ingredient(category, name, calories, calPer100, kJPer100);
                ingredients.add(ingredient);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return ingredients;
    }

    @Override
    public int getNumIngredient() throws FileNotFoundException {
        List<IngredientInterface> ingredientList = readData("ingredients.csv", ",");
        int numIngredientList = 0;
        for (int i = 0 ;  i < ingredientList.size() ; i++){
            numIngredientList += 1;
        }
        return numIngredientList;
    }

    @Override
    public int getNumCategories() throws FileNotFoundException {
        List<IngredientInterface> ingredientList = readData("ingredients.csv", ",");
        int numCategoryList = 0;
        for (int i = 0 ;  i < ingredientList.size() ; i++){
            ArrayList<IngredientInterface> categoryList = new ArrayList<>();
            categoryList.add(ingredientList.get(0));
            if (categoryList.contains(ingredientList.get(0))){
                numCategoryList += 1;
            }

        }
        return numCategoryList;
    }


    @Override
    public List<IngredientInterface> getReplacements(String ingredient) throws FileNotFoundException {
        List<IngredientInterface> ingredientsList = readData("ingredients.csv", ",");
        int loc = 0;
        List<IngredientInterface> list = null;
        for (int i = 0; i < ingredientsList.size(); i++) {
            if (ingredientsList.get(i).getName().equals(ingredient)) {
                loc = i;
                Iterator<IngredientInterface> its = ingredientsList.iterator();
                IngredientInterface ingredientObject = ingredientsList.get(loc);
                list = new ArrayList<IngredientInterface>();
                IngredientInterface current;
                for (int j = 0; j < 3; j++) {
                    if (!its.hasNext()) {
                        return list;
                    } else {
                        current = its.next();
                        if (current.getCalories() < ingredientObject.getCalories() + 10
                                || current.getCalories() > ingredientObject.getCalories()) {
                            list.add(current);
                        }
                    }
                }
                break;
            }

        }
        return list;


    }


    private IterableMultiKeyRBT<Ingredient> rbt;
    List<Ingredient> ingredient = new ArrayList<>();
    public BackendImplementation(IterableMultiKeyRBT iterableMultiKeyRBT) {
        rbt = iterableMultiKeyRBT;
    }

    /**
     * Main method of the backend class.
     * @param args
     */
    public static void main(String[] args) {
        // The iterable red-black tree.
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
        // Scanner that takes input of the user.
        Scanner scnr = new Scanner(System.in);
        // Backend reference.
        BackendImplementation backend = new BackendImplementation(rbt);
        // Frontend reference.
        Frontend frontend = new Frontend(backend, scnr);
        // Call to the main loop of the frontend to display the menu and
        // start the application.
        frontend.mainCommands();
    }

}
