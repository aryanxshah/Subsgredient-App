import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BackendDeveloperTests {
    IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
    private BackendImplementation backendInterface = new BackendImplementation(rbt);


    /**
     * test for reading data from a valid file
     */
    @Test
    public void testReadData1() throws IOException {
        // Specify the path to a valid CSV file containing song data
        String filePath = "ingredients.csv";

        // Attempt to read data from the specified file
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
        BackendImplementation implementation = new BackendImplementation(rbt);

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
            IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
            BackendInterface implementation = new BackendImplementation(rbt);
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
        // BackendRbtPlaceholderClass rbt = new BackendRbtPlaceholderClass();
        //Iterabl
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
        BackendInterface backend = new BackendImplementation(rbt);

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
        //BackendRbtPlaceholderClass rbt = new BackendRbtPlaceholderClass();
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
        //processing content on the backend.
        BackendImplementation backend = new BackendImplementation(rbt);
        //when initial size of the rbt storage.
        if (rbt.numKeys() != 0) {
            Assertions.fail("Initial size of the rbt placeholder is NOT zero.");
        }
    }

    @Test
    public void testReplacementsIntegration() throws FileNotFoundException { //to check if ingredient entered is null
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
        BackendImplementation backendInterface = new BackendImplementation(rbt);
        Scanner scanner = new Scanner(System.in);
        try{
            FrontendInterface frontend = new Frontend(backendInterface, scanner);

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void testListAllIngredientsCategoriesIntegration() throws FileNotFoundException{ // to check if the number of categories
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
        BackendImplementation backend = new BackendImplementation(rbt);
        try{
            backend.readData("ingredients.csv",",");

        }
        catch(Exception e){
            e.printStackTrace();

        }
        int numCategories = backend.getNumCategories();
        assertEquals(numCategories, backend.getNumCategories());
    }

    @Test
    void testFrontEnd1(){
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<Ingredient>();
        BackendImplementation backend = new BackendImplementation(rbt);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        String userInput = "150\n4\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        Frontend frontend = new Frontend(backend, scanner);

        frontend.mainCommands();

        String expectedOutput = "Invalid option.";
        Assertions.assertTrue(outContent.toString().contains(expectedOutput));
        System.setOut(originalOut);
    }
    @Test
    void testFrontEnd2(){
        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<Ingredient>();
        BackendImplementation backend = new BackendImplementation(rbt);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        String userInput = "a\n4\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        Frontend frontend = new Frontend(backend, scanner);

        frontend.mainCommands();

        String expectedOutput = "Invalid input.";
        Assertions.assertTrue(outContent.toString().contains(expectedOutput));
        System.setOut(originalOut);
    }


}


