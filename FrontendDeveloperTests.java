import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class FrontendDeveloperTests {

    @BeforeEach
    public void setUp() {
        BackendInterface backend = new BackendPlaceholder();
        backend = new BackendPlaceholder();
        try
        {
            backend.readData("ingredients.csv", ",");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }

    /**
     * Method that tests if a file loads as a valid file
     */
    @Test
    public void testLoadValidFile() throws FileNotFoundException {
        BackendInterface backend = new BackendPlaceholder();
        // Assuming loadFile loads a valid file
        Frontend frontend = new Frontend(backend, new Scanner("valid_file.txt"));
        boolean loadResult = frontend.loadFile();
        assertTrue(loadResult);
    }

    // /**
    //  * Method that tests if a file loads as an invalid file
    //  */
    // @Test
    // public void testLoadInvalidFile() throws FileNotFoundException {
    //     // Assuming loadFile loads an invalid file
    //     Frontend frontend = new Frontend(backend, new Scanner("invalid_file.txt"));
    //     boolean loadResult = frontend.loadFile();
    //     assertFalse(loadResult);
    // }

    /**
     * Method that checks if replacements for an ingredient are valid
     */
    @Test
    public void testListReplacementsForValidIngredient() throws NullPointerException {// Corrected method name
      BackendInterface backend = new BackendPlaceholder();
      // You may want to add more assertions here to check the behavior of the iterator
      Frontend frontend = new Frontend(backend, new Scanner("Passion Fruit"));
      Iterator<IngredientInterface> validReplacements = frontend.listReplacements();
      assertNotNull(validReplacements);
    }

    @Test
    public void testListAllIngredientsCategories() {
        BackendInterface backend = new BackendPlaceholder();
        TextUITester test = new TextUITester("3");
        Scanner scanner = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, scanner);
        frontend.numIngredientsCategories();

        String expected = "Number of Ingredients: 7\n"+"Number of Categories: 3\n";
        assertEquals(expected, test.checkOutput());
    }

    @Test
    public void testListReplacementsForInvalidIngredient() {
      BackendInterface backend = new BackendPlaceholder();
        TextUITester test = new TextUITester("2");
        Scanner scanner = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, scanner);
        frontend.listReplacements();

        String expected = "Enter the name of the ingredient you would like to replace: \n";
        assertEquals(expected, test.checkOutput());
    }

    /**
     * Method that tests exitApp
     */
    @Test
    public void testExitApp() {
        BackendInterface backend = new BackendPlaceholder();
        TextUITester test = new TextUITester("4");
        Scanner scanner = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, scanner);
        frontend.exitApp();

        String expected = "Exiting Subsgredient App!\n";
        assertEquals(expected, test.checkOutput());
    }
    /**
     * Test that tests if you can read a valid file from the backend
     */
    // @Test
    // public void testValidFileRead() {
    //     BackendPlaceholder fileReader = new BackendPlaceholder(); // Create an instance of your file reader class

    //     // Replace "validFilePath" with the path to a valid file you want to test
    //     String validFilePath = "path_to_valid_file.txt";
    //     String fileContent = fileReader.readData(validFilePath, String delimiter);

    //     // Assert that the file content is not null
    //     assertNotNull(fileContent);
    //     assertTrue(fileContent.contains("expected_text"));

    // }
    /**
     * Method that checks if replacements for an ingredient are invalid
     */
    // @Test
    // public void integrationTestListReplacementsForInvalidIngredient() {

    //     frontend.numIngredientsCategories(); // Corrected method name
    //     IngredientInterface invalidIngredient;
        


    //     try {
    //         assertNotNull(invalidReplacements);
    //         assertFalse(invalidReplacements.hasNext());
    //     } catch (NullPointerException nullPointerException) {
    //         assertFalse(false);
    //         // Handle the NullPointerException if it occurs
    //        // fail("NullPointerException occurred: " + nullPointerException.getMessage());
    //     }
    // }

    

    

}