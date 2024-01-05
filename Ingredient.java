public class Ingredient implements IngredientInterface, Comparable<Ingredient> {
    private String category, name;
    private int calories;
    private String calPer100, kJPer100;
    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCalories() {
        return this.calories;
    }

    @Override
    public String getcalPer100() {
        return this.calPer100;
    }

    @Override
    public String getkJPer100() {
        return this.kJPer100;
    }


    @Override
    public int getIngredientNamesCompare(Ingredient o) {
        if (o.getName().compareTo(this.getName()) == 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    @Override
    public int getIngredientCaloriesCompare(Ingredient o) {
        if (o.getCalories() == this.getCalories()){
            return 0;
        }
        else{
            return -1;
        }
    }
    

    public Ingredient(String category, String name, int calories, String calper100, String kJper100) {
        this.category = category;
        this.name = name;
        this.calories = calories;
        this.calPer100 = calPer100;
        this.kJPer100 = kJPer100;
    }
    
    @Override
    public int compareTo(Ingredient o) {
	if (o.getCalories() - this.getCalories() <= 20 || o.getCalories() - this.getCalories() >= -20){
            return 0;
        }
        else if (o.getCalories() == this.getCalories()){
            return 0;
        }
        else if(this.getCalories() > o.getCalories()){
            return 1;
        }
        else{
            return -1;
        }
    }    
}
