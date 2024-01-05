import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        List<IngredientInterface> ingredientList = readData("ingredients.csv", ",");
        System.out.println(ingredientList);
        List<IngredientInterface> replacement = new ArrayList<>(); // Initialize the replacement list
        int loc = 0;
        for (int i = 0; i < ingredientList.size(); i++) {
            if (ingredientList.get(i).getName().equals(ingredient)) {
                loc = i;
                break;
            }
        }
        String category = ingredientList.get(loc).getCategory();
        int calories = ingredientList.get(loc).getCalories();
        for (int i = 0; i < ingredientList.size(); i++) {
            String thisCategory = ingredientList.get(i).getCategory();
            int thisCalories = ingredientList.get(i).getCalories();
            IngredientInterface thisName = ingredientList.get(i);
            if (category.equals(thisCategory) && (thisCalories == calories || thisCalories - calories == 20)) {
                replacement.add(thisName);
            }
        }
        return replacement;
    }

}
