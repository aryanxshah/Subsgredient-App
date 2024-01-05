import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BackendPlaceholder implements BackendInterface {
  /**
   * Loads ingredient data from the specified CSV file for the interface
   *
   * @param dataFilePath - the path to the CSV file containing ingredients data
   * @param delimiter    - the delimiter used in the CSV file for separating values
   * @return the ingredient data from a CSV file
   */
  @Override
  public List<IngredientInterface> readData(String dataFilePath, String delimiter) throws FileNotFoundException {
    List<IngredientInterface> ingredientList = new ArrayList<>();

    ingredientList.add( new IngredientFD("Vegetable", "Carrot", 41, "41", "173"));
    ingredientList.add( new IngredientFD("Fruit", "Apple", 52, "52", "218"));
    ingredientList.add( new IngredientFD("Fruit", "Banana", 89, "89", "373"));
    ingredientList.add( new IngredientFD("Vegetable", "Broccoli", 55, "55", "230"));
    ingredientList.add( new IngredientFD("Fruit", "Orange", 43, "43", "180"));
    ingredientList.add( new IngredientFD("Protein", "Chicken Breast", 165, "165", "690"));
    ingredientList.add( new IngredientFD("Fruit", "Passion Fruit", 97, "97", "406"));
    
    return ingredientList;
  }

  /**
   * Calculates the number of ingredients as dataset statistics
   *
   * @return dataset of ingredients and categories
   */
  @Override
  public int getNumIngredient() {
      return 7;
  }

  /**
   * Calculates the number of categories as dataset statistics
   *
   * @return dataset of categories
   */
  @Override
  public int getNumCategories() {
      return 3;
  }

  /**
   * placeholder code for getReplacements
   *
   * @param fruit a fruit that needs to be replaced
   * @return a list of ingredients
   */
  @Override
  public List<IngredientInterface> getReplacements(String ingredientName) {
      List<IngredientInterface> replacements = new ArrayList<>();
      IngredientInterface ingredient = new IngredientFD("Fruit", "Strawberry", 97, "97", "406");
      replacements.add(ingredient);
      return replacements;
  }


  class IngredientFD implements IngredientInterface, Comparable<Ingredient> {
      private String category;
      private String name;
      private int calories;
      private String calPer100;
      private String kJPer100;


      public IngredientFD(String category, String name, int calories, String calPer100, String kJPer100) {
          this.category = category;
          this.name = name;
          this.calories = calories;
          this.calPer100 = calPer100;
          this.kJPer100 = kJPer100;
      }

      
      public String getCategory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
      }

      
      public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
      }

      
      public int getCalories() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCalories'");
      }

      
      public String getcalPer100() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getcalPer100'");
      }

      
      public String getkJPer100() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getkJPer100'");
      }

      
      public int getIngredientNamesCompare(Ingredient o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngredientNamesCompare'");
      }

      
      public int getIngredientCaloriesCompare(Ingredient o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngredientCaloriesCompare'");
      }

      
      public int compareTo(Ingredient o) {
        
        return 0;
      }
  }
}