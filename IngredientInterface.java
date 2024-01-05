/**
 * Interface of a single ingredient
 */
public interface IngredientInterface extends Comparable<Ingredient>{

    public String getCategory();

    public String getName();

    public int getCalories();

    public String getcalPer100();

    public String getkJPer100();

    public int getIngredientNamesCompare(Ingredient o);

    public int getIngredientCaloriesCompare(Ingredient o);


  /*
  public IngredientImplementation(String category, String name, int calories,
  String compareIngredientNames, double compareIngredientCalories) {
    this.category = category;
    this.name = name;
    this.calories = calories;
    this.compareIngredientNames = compareIngredientNames;
    this.compareIngredientCalories = compareIngredientCalories;
  }
   */
  public int compareTo(Ingredient o);
}
