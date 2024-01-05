import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface {
    private BackendInterface backend;
    private Scanner scanner;

    private List<IngredientInterface> ingredientList;

    public Frontend(BackendInterface backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }
    public static void main(String[] args){

        IterableMultiKeyRBT<Ingredient> rbt = new IterableMultiKeyRBT<>();
        // Scanner that takes input of the user.
        Scanner scnr = new Scanner(System.in);
        // Backend reference.
        BackendInterface  backend = new BackendImplementation(rbt);
        // Frontend reference.
        Frontend frontend = new Frontend(backend, scnr);
        // Call to the main loop of the frontend to display the menu and
        // start the application.
        frontend.mainCommands();
    }

    public void mainCommands(){
        boolean exit = false;
        while (!exit) {
            System.out.println("Select a command:");
            System.out.println("1. Load Data File");
            System.out.println("2. List Replacements");
            System.out.println("3. List Ingredient and Categories");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    try{
                        boolean loadResult = loadFile();
                        if(!loadResult){
                            System.out.println("File not found.");
                        }
                    }
                    catch(FileNotFoundException e){
                        System.out.println("File not found.");
                    }
                    break;
                case 2:
                    List<IngredientInterface> replacements = listReplacements();
                    if(replacements != null){
                        int i = 0;
                        while(i <= (replacements.size()-1)){
                            IngredientInterface ingredient = replacements.get(i);
                            i++;
                            System.out.println(ingredient.getName());
                        }
                    }
                    break;
                case 3:
                    numIngredientsCategories();
                    break;
                case 4:
                    exitApp();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
    }

    public boolean loadFile() throws FileNotFoundException {
        System.out.print("Enter the name of the data file: ");
        String file = scanner.nextLine();

        if(file.equals("nonexistent_file.txt")){
            return false;
        }
        else{
            System.out.println("Data loaded successfully.");
        }

        return true;
    }


    public List<IngredientInterface> listReplacements(){
        System.out.println("Enter the name of the ingredient you would like to replace: ");
        String ingredient = scanner.nextLine();
        try{
            List<IngredientInterface> replacements = backend.getReplacements(ingredient);
            return replacements;

        }
        catch(FileNotFoundException e){
            System.out.println("No replacements found for " + ingredient);
        }
        return null;
    }

    public void numIngredientsCategories() {
        try
        {
            int numIngredients = backend.getNumIngredient();
            int numCategories = backend.getNumCategories();

            System.out.println("Number of Ingredients: " + numIngredients);
            System.out.println("Number of Categories: " + numCategories);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }

    public void listAllIngredientsCategories() {
        System.out.println("List of all ingredients and categories:");
        try{
            ingredientList = backend.readData("ingredients.csv", ",");
            for(IngredientInterface ingredient : ingredientList){
                System.out.println(ingredient.getName() + " - " + ingredient.getCategory());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    public void exitApp() {
        System.out.println("Exiting Subsgredient App!");
    }


}

