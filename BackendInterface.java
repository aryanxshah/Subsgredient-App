
import java.util.ArrayList;
import java.util.Iterator;

import java.io.FileNotFoundException;

import java.util.List;

/**
 * Interface for the backend functionality
 */

public interface BackendInterface {

  // List<IngredientInterface> ingredientList = new ArrayList<>();

	/**
	 * Loads ingredient data from the specified CSV file for the interface
	 *
	 * @param dataFilePath - the path to the CSV file containing ingredients data
	 * @param delimiter - the delimiter used in the CSV file for separating values
	 * @return the ingredient data from a CSV file
	 */
	public List<IngredientInterface> readData(String dataFilePath,String delimiter) throws FileNotFoundException;

	/**
	 * Calculates the number of ingredients as dataset statistics
	 *
	 * @return dataset of ingredients and categories
	 */
	public int getNumIngredient() throws FileNotFoundException;

	/**
	 * Calculates the number of categories as dataset statistics
	 *
	 * @return dataset of categories
	 */
	public int getNumCategories() throws FileNotFoundException;
  
	/**
	 * Gets a list of ingredients that can replace the given fruit ("Passion Fruit")
	 *
	 * @param ingredient a fruit that needs to be replaced
	 * @return a list of ingredients
	 */
	public List<IngredientInterface> getReplacements(String ingredient) throws FileNotFoundException;

  

}

