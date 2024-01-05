import org.junit.jupiter.api.Test;
import org.junit.Assert.assertFalse;
import org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BackendDeveloperTest {
    private BackendImplementation backendInterface = new BackendImplementation();


    /**
     * test for reading data from a valid file
     */
    @Test
    public void testReadData1() throws IOException {
        // Specify the path to a valid CSV file containing song data
        String filePath = "/Users/tanyasanthosh/IdeaProjects/P12.InterfaceDesign/src/ingredients.csv";

        // Attempt to read data from the specified file
        BackendImplementation implementation = new BackendImplementation();

        implementation.readData(filePath);
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
            String filePath = "/Users/tanyasanthosh/IdeaProjects/P12.InterfaceDesign/src/ingredients.csv";
            List<Ingredient> file = backendInterface.readData(filePath);

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
        BackendRbtPlaceholderClass<Ingredient> rbt = new BackendRbtPlaceholderClass<>();
        BackendImplementation backend = new BackendImplementation();

        // Creates multiple ingredient objects that are to be added to the list.
        Ingredient activia = new Ingredient("Yogurt","Activia",100,"74","311");
        Ingredient activiaLemon = new Ingredient("Yogurt","Activia Lemon",100,"100ml", "420 KJ");
        Ingredient aloeVeraYogurt = new Ingredient("Yogurt","Aloe Vera Yogurt",100,"85ml","357 KJ");
        Ingredient activiaStrawberry = new Ingredient("Yogurt","Activia Strawberry",100,"97ml","407 KJ");

        // Added all ingredients to the list.
        rbt.insertSingleKey(activia);
        rbt.insertSingleKey(activiaLemon);
        rbt.insertSingleKey(aloeVeraYogurt);
        rbt.insertSingleKey(activiaStrawberry);

        List<Ingredient> actual = backendInterface.getReplacements("Activia Strawberry");
        List<String> expected = Arrays.asList("Activia Strawberry", "Cherry Yogurt", "Fruit Yogurt" , "Peach Yogurt");

        List<String> actualNames = new ArrayList<>();
        for (Ingredient ingredient : actual) {
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
        BackendRbtPlaceholderClass<Ingredient> rbt = new BackendRbtPlaceholderClass<>();
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
        BackendRbtPlaceholderClass<Ingredient> rbt = new BackendRbtPlaceholderClass<>();

        // Ingredient objects.
        Ingredient islandDressing = new Ingredient("Sauces&Dressings", "1000 Island Dressing", 370,"100ml","1554 kJ" );
        Ingredient acerola = new Ingredient("Tropical&ExoticFruits","Acerola",	32,	"100g",	"407 kJ");
        Ingredient bakedChicken = new Ingredient("Dishes&Meals","Baked Chicken",164,"100g","689kJ");
        Ingredient beanBurrito = new Ingredient("FastFood","Bean Burrito",200,"100g","840kJ");

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

    @Test
    public void testReplacementsIntegration() throws FileNotFoundException { //to check if ingredient entered is null
        BackendImplementation backendInterface = new BackendImplementation();
        Scanner scanner = new Scanner(System.in);
        FrontendInterface frontendInterface = new FrontendInterface( backendInterface, scanner );
        Ingredient ingredient = new Ingredient("Yogurt","Activia Strawberry",100,"97ml", "407 KJ");
        Iterator<IngredientInterface> replacements = frontendInterface.replacements(ingredient);
        assertNotNull(replacements);
    }

    @Test
    public void testListAllIngredientsCategoriesIntegration(){ // to check if the number of categories
        BackendImplementation backend = new BackendImplementation();
        TextUITester test = new TextUITester("3");
        Scanner scanner = new Scanner(System.in);
        FrontendInterface frontend = new FrontendInterface(backend, scanner);
        frontend.listAllIngredientsCategories();
        String expected = "Number of Ingredients: 1994\n"+"Number of Categories: 1994\n";
        assertEquals(expected, test.checkOutput());
    }


}




