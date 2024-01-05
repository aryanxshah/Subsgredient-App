import java.util.Scanner;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class drives an interactive loop of prompting the user to select a
 * command, then requests any required details about that command from the user,
 * and then displays the results of the command. The commands to include are: a
 * command to specify(and load) a data file, a command that lists up to three
 * replacements (with the same or minimally higher caloric value than the
 * original ingredient) for an ingredient specified by the user, a command to
 * list the number of ingredients and the number of categories, and a command to
 * exit the app. The results are computed with the help of the BD's code. When
 * the user enters invalid input, instructive feedback about what they should
 * enter is displayed.
 * 
 * @author Trevor Garski, Aryan Tarak Shah, and Enrique Villasenor
 */
public interface FrontendInterface {

//	/**
//	 * Leave this constructor commented out until implementation. 
//	 * This constructor is meant to interact with the backend implementation.
//	 * 
//	 * @param backend - accepts a reference from the backend implementation
//	 * @param scnr - Scanner to read user input
//	 */
//	public FrontendInterface(BackendInterface backend, Scanner, scnr) {
//		 
//	 }

	/**
	 * This method prompts the user to pick a command out of the provided options.
	 * Then, depending on the command, this main method will call other methods to
	 * perform the user's intention.
	 */
	public void mainCommands();

	/**
	 * This method reads a data file specified by the user for use later in the
	 * implementation.
	 * 
	 */
	public boolean loadFile() throws FileNotFoundException;

	/**
	 * This method calls on the backend developers' class to traverse through their
	 * Red-Black tree to find an adequate replacement for the ingredient based on
	 * food category and caloric quantity.
	 * 
	 */
	public Iterator<IngredientInterface> listReplacements();

	/**
	 * This method calls upon the backend developers tree's size(for number of
	 * ingredients) and number of categories to help give the user more information
	 * on the ingredients in the data set.
	 */
	public void listAllIngredientsCategories();

	/**
	 * This method simply exits the Subsgredient App!
	 */
	public void exitApp();

}
