import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackendDeveloperTests {
    private BackendImplementation backendInterface = new BackendImplementation();


    /**
     * test for reading data from a valid file
     */
    @Test
    public void testReadData1() throws IOException {
        // Specify the path to a valid CSV file containing song data
        String filePath = "ingredients.csv";

        // Attempt to read data from the specified file
        BackendImplementation implementation = new BackendImplementation();

        implementation.readData(filePath, ",");
//SEE THE CODE HERE
        // Assert that the data is not null and that there are 600 songs in the list as well as 1
        // column name row
//        Assertions.assertEquals(600, rbt.size());

    }

    /**
     * Method to test the number of ingredients in each category.
     */
    @Test
    public void testGetNumIngredient() throws FileNotFoundException {
        int actual = backendInterface.getNumIngredient();
        int expected = 1993;
        Assertions.assertEquals(expected,actual);
    }



    //
    @Test
    public void testreadData2() {
        try {
            String filePath = "ingredients.csv";
            BackendInterface implementation = new BackendImplementation();
            implementation.readData(filePath, ",");
        }
        catch(Exception e){

            String expected = "File not found";
            if(!e.getMessage().equals(expected)) {
                Assertions.fail("Test failed");
            }

        }
    }
//
    @Test
    public void testGetReplacements2() throws FileNotFoundException {
        BackendRbtPlaceholderClass rbt = new BackendRbtPlaceholderClass();
        BackendInterface backend = new BackendImplementation();

        // Creates multiple ingredient objects that are to be added to the list.
        IngredientInterface activia = new Ingredient("Yogurt","Activia",100,"74","311");
        IngredientInterface activiaLemon = new Ingredient("Yogurt","Activia Lemon",100,"100ml", "420 KJ");
        IngredientInterface aloeVeraYogurt = new Ingredient("Yogurt","Aloe Vera Yogurt",100,"85ml","357 KJ");
        IngredientInterface activiaStrawberry = new Ingredient("Yogurt","Activia Strawberry",100,"97ml","407 KJ");

        // Added all ingredients to the list.
        rbt.insertSingleKey(activia);
        rbt.insertSingleKey(activiaLemon);
        rbt.insertSingleKey(aloeVeraYogurt);
        rbt.insertSingleKey(activiaStrawberry);

        // Gets the list of replacements for the given ingredient.
        List<IngredientInterface> actual = backend.getReplacements("Activia Strawberry");

        List<String> expected = Arrays.asList("Activia Strawberry", "Cherry Yogurt", "Fruit Yogurt" , "Peach Yogurt");

        List<String> actualNames = new ArrayList<>();
        for (IngredientInterface ingredient : actual) {
            actualNames.add(ingredient.getName());
        }

        Assertions.assertEquals(expected, actualNames);
    }


    /**
     * JUnit test to check if the initialization of the placeholder class
     * is a valid one or not, i.e., if its size is zero when initialized.
     * If the size is not zero, then test fails with custom message, else
     * it fails.
     */
    @Test
    public void testInitializationOfPlaceholder() {
        //stores the contents of the file in the rbt placeholder.
        BackendRbtPlaceholderClass rbt = new BackendRbtPlaceholderClass();
        //processing content on the backend.
        BackendImplementation backend = new BackendImplementation();
        //when initial size of the rbt storage.
        if (rbt.numKeys() != 0) {
            Assertions.fail("Initial size of the rbt placeholder is NOT zero.");
        }
    }

    /**
     * JUnit test to check if the size of the rbt placeholder where we
     * store all the songs is correctly reported by the numKeys() method or
     * not. If it is not equal, the test fails with custom message. Else,
     * it passes.
     */
@Test
    public void validSizeOfThePlaceholder() {
        //stores the contents of the file in
        // the rbt placeholder.
        BackendRbtPlaceholderClass rbt = new BackendRbtPlaceholderClass();

        // Ingredient objects.
        IngredientInterface islandDressing = new Ingredient("Sauces&Dressings", "1000 Island Dressing", 370,"100ml","1554 kJ" );
        IngredientInterface acerola = new Ingredient("Tropical&ExoticFruits","Acerola",  32,     "100g", "407 kJ");
        IngredientInterface bakedChicken = new Ingredient("Dishes&Meals","Baked Chicken",164,"100g","689kJ");
        IngredientInterface beanBurrito = new Ingredient("FastFood","Bean Burrito",200,"100g","840kJ");

        //inserting the ingredients in the rbt placeholder.
        rbt.insertSingleKey(islandDressing);
        rbt.insertSingleKey(acerola);
        rbt.insertSingleKey(bakedChicken);
        rbt.insertSingleKey(beanBurrito);

        int actual = rbt.numKeys();
        int expected = 4;
        // Checks the correctness of the size.
        if (expected != actual) {
            Assertions.fail("There are incorrect number of ingredients in the placeholder, " +
                    "or that the size() method is reporting the wrong size of placeholder.");
        }
    }

}
