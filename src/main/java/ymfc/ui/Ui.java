package ymfc.ui;

import ymfc.ingredient.Ingredient;
import ymfc.recipe.Recipe;
import ymfc.recipe.RecommendedRecipe;

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class representing the user interface for interacting with the user.
 */
public class Ui {
    private static final String LINE = "\t_________________________________________" +
            "_________________________________________";
    private static final String LOGO = "                 (\\" + System.lineSeparator() +
            "                  \\ \\" + System.lineSeparator() +
            "              __    \\/ ___,.-------..__        __" + System.lineSeparator() +
            "             //\\\\ _,-'\\\\               `'--._ //\\\\" + System.lineSeparator() +
            "             \\\\ ;'      \\\\                   `: //" + System.lineSeparator() +
            "              `(          \\\\                   )'" + System.lineSeparator() +
            "                :.          \\\\,----,         ,;" + System.lineSeparator() +
            "                 `.`--.___   (    /  ___.--','" + System.lineSeparator() +
            "                   `.     ``-----'-''     ,'" + System.lineSeparator() +
            "                      -.               ,-" + System.lineSeparator() +
            "                         `-._______.-'" + System.lineSeparator();

    private final String listOfCommands =
            "\tThis is all the things you can do with me:\n"
            + "\t\t0. help                              -  Lists out all the available commands and their usage\n"
            + LINE
            + "\n\tThe following commands is for working with your COOKBOOK\n"
            + "\t\t1. list                              -  Lists out all the existing recipes\n"
            + "\t\t2. add n/NAME i/INGREDIENTS... sn/STEPn... [c/CUISINE] [t/TIME]              " +
                    "-  Adds a new recipe.\n"
            + "\t\t\t the cuisine and time parameters are optional\n"
            + "\t\t\t add more ingredients using more i/ tags\n"
            + "\t\t\t add more steps using more sx/ tags, where x starts from 1 and increases one by one\n"
            + "\t\t3. sort s/name                       -  Sorts the recipes alphabetically by name\n"
            + "\t\t4. sort s/time                       -  Sorts the recipes by time, in ascending order\n"
            + "\t\t\t the s/name and s/time MUST be in lowercase\n"
            + "\t\t5. find [OPTIONS/]KEYWORDS           -  Looks for the keyword in recipes' \n"
            + "\t\t\t The options is any permutation of n, i and s. Some examples include:\n"
            + "\t\t\t find name             = name (leaving the options blank will default look for the name)\n"
            + "\t\t\t find i/keyword        = ingredients\n"
            + "\t\t\t find ns/keyword       = name or steps\n"
            + "\t\t\t find nis/keyword      = name or ingredients or steps\n"
            + "\t\t6. edit e/NAME [n/NAME] [i/INGREDIENTS...] [sn/STEPn...] [c/CUISINE] [t/TIME]  " +
                    "-  Edit an existing recipe\n"
            + "\t\t\t All parameters are optional. Any parameter you exclude will not be edited (remain unchanged)\n"
            + "\t\t\t The format for adding multiple ingredients and steps are the same as the add command\n"
            + "\t\t\t For ingredients and steps, the entire group of ingredients and steps will be overwritten\n"
            + "\t\t\t You cannot choose specific ingredients or steps to edit! They will be overwritten as a whole.\n"
            + "\t\t\t For cuisine and time, if you write c/ or t/ respectively and leave the CUISINE and TIME empty,\n"
            + "\t\t\t then the current cuisine and/or time parameter(s) will be deleted\n"
            + "\t\t7. delete n/NAME                     -  Deletes the recipe of that name\n"
            + "\t\t8. recommend                         -  Ranks recipes based on how many ingredients are available\n"
            + "\t\t9. random                            -  Picks a recipe at random and shows it to you\n"
            + "\t\t10. bye                              -  Ends the program\n"
            + LINE
            + "\n\tThe following commands is for working with your INVENTORY\n"
            + "\t\t1. new n/INGREDIENT                  -  Add a new ingredient to your inventory\n"
            + "\t\t2. listI                             -  Lists out all your current ingredients\n"
            + "\t\t3. findI INGREDIENT                  -  Looks for your ingredient in your inventory\n"
            + "\t\t4. deleteI n/INGREDIENT              -  Deletes the ingredient of that name\n"
            + "\t\t5. sortI                             -  Sorts your ingredients alphabetically by name\n"
            + LINE
            + "\n\tGot it? Let's get back to cooking.\n"
            + "\t If you still need more details, go read the User Guide!\n";

    private Scanner userInput;

    /**
     * Constructor for a <code>YMFC.Ui</code> object.
     * Overloads default constructor to initialise a scanner object reading user inputs.
     *
     * @param input InputStream for the Ui class to read commands from.
     */
    public Ui(InputStream input) {
        this.userInput = new Scanner(input);
    }

    /**
     * Display a greeting message.
     */
    public void greet() {
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println("\tGreetings, this is YMFC.");
        System.out.println("\tGot a recipe? Pass it to me.");
        System.out.println("\tNeed a recipe? Just ask me.");
        System.out.println("\tNeed your dishes washed? Get off your couch.");
        System.out.println(LINE);
    }

    /**
     * Display a goodbye message.
     */
    public void bidFarewell() {
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println("\tBye bye, come again!");
        System.out.println(LINE);
    }

    /**
     * Return user input as a string when user hit enter key.
     * Users are re-prompted if they enter an empty command.
     *
     * @return String representing what user typed into the program.
     */
    public String readCommand() {
        String commandRead = "";
        while (commandRead.isEmpty()) {
            commandRead = userInput.nextLine().trim();
        }
        assert !commandRead.isEmpty() : "User input is empty";
        return commandRead;
    }

    /**
     * Displays an error message.
     *
     * @param error Error message to be displayed.
     */
    public void printErrorMessage(String error) {
        System.out.println(error);
    }

    /**
     * Display a newly added recipe.
     *
     * @param addedRecipe Recipe that has been added.
     * @param listCount Number of recipes currently in the list.
     */
    public void printAddedRecipe(String addedRecipe, int listCount) {
        System.out.println(LINE);
        System.out.println("\tOkie dokie, one more recipe for me:");
        System.out.println("\t " +  addedRecipe);
        // Conditional operator to pluralize "recipe" when listCount above 1
        System.out.println("\tYou currently have " + listCount +
                (listCount <= 1 ? " recipe" : " recipes") + " in your list.");
        System.out.println(LINE);
    }

    /**
     * Display a newly added ingredient.
     *
     * @param addedIngredient Ingredient that has been added.
     * @param listCount Number of ingredients currently in the list.
     */
    public void printAddedIngredient(String addedIngredient, int listCount) {
        System.out.println(LINE);
        System.out.println("\tI'll add that to your inventory:");
        System.out.println("\t " +  addedIngredient);
        // Conditional operator to pluralize "ingredient" when listCount above 1
        System.out.println("\tYou currently have " + listCount +
                (listCount <= 1 ? " ingredient" : " ingredients") + " in your inventory.");
        System.out.println(LINE);
    }

    /**
     * Display the entire list of recipe.
     *
     * @param list ArrayList of recipes to be displayed.
     * @param listCount Integer representing total number of recipes in <code>list</code>.
     */
    public void printList(ArrayList<Recipe> list, int listCount) {
        System.out.println(LINE);
        System.out.println("\tHere's everything in my collection so far:");
        printListWithOrder(list, listCount);
    }

    /**
     * Display list of recipes with order of each recipe.
     *
     * @param list ArrayList of recipes to be displayed.
     * @param listCount Integer representing total number of recipes in <code>list</code>.
     */
    private void printListWithOrder(ArrayList<Recipe> list, int listCount) {
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
            System.out.println(LINE);
        }
    }

    /**
     * Display the entire list of ingredients.
     *
     * @param list ArrayList of ingredients to be displayed.
     * @param listCount Integer representing total number of ingredients in <code>list</code>.
     */
    public void printIngredientList(ArrayList<Ingredient> list, int listCount) {
        System.out.println(LINE);
        System.out.println("\tHere's all the ingredients you currently have: ");
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Display a recipe that been deleted.
     *
     * @param deletedRecipe Recipe that has been deleted.
     * @param listCount Integer representing number of recipes in the list currently.
     */
    public void printDeletedTask(String deletedRecipe, int listCount) {
        System.out.println(LINE);
        System.out.println("\tAww, I shall begrudgingly let go of this recipe:");
        System.out.println("\t  " + deletedRecipe);
        // Conditional operator to pluralize "recipe" when listCount above 1
        System.out.println("\tYou currently have " + listCount +
                (listCount == 1 ? " recipe" : " recipes") + " in your list.");
        System.out.println(LINE);
    }

    /**
     * Display an ingredient that been deleted.
     *
     * @param deletedIngredient Ingredient that has been deleted.
     * @param listCount Integer representing number of ingredients in the list currently.
     */
    public void printDeletedIngredient(String deletedIngredient, int listCount) {
        System.out.println(LINE);
        System.out.println("\tAww, I shall begrudgingly let go of this ingredient:");
        System.out.println("\t  " + deletedIngredient);
        System.out.println("\tYou currently have " + listCount +
                (listCount == 1 ? " ingredient" : " ingredients") + " in your list.");
        System.out.println(LINE);
    }

    /**
     * Display the list of available commands.
     */
    public void printHelp() {
        System.out.println(LINE);
        System.out.println(listOfCommands);
        System.out.println(LINE);
    }

    /**
     * Alert user that the recipe being added already exists.
     *
     * @param duplicateName Name of the duplicate recipe.
     */
    public void printDuplicateRecipe(String duplicateName){
        System.out.println(LINE);
        System.out.println("\tThere already exists a recipe called: " + duplicateName + "!");
        System.out.println(LINE);
    }

    public void printDuplicateIngredient(String duplicateName) {
        System.out.println(LINE);
        System.out.println("\tThere already exists an ingredient called: " + duplicateName + "!");
        System.out.println(LINE);
    }

    /**
     * Display list of recipes that match the user's parameters.
     *
     * @param list ArrayList of recipes to be displayed.
     * @param listCount Integer representing total number of recipes in <code>list</code>.
     */
    public void printFind(ArrayList<Recipe> list, int listCount) {
        System.out.println(LINE);
        System.out.println("\tHere's everything that I've found so far:");
        printListWithOrder(list, listCount);
        System.out.println("\tTotal: " + listCount + " recipes found!");
        System.out.println(LINE);
    }

    /**
     * Custom message when no recipe is found with FindCommand
     */
    public void printEmptyFind() {
        System.out.println(LINE);
        System.out.println("\tSorry I didn't find anything! :(");
        System.out.println("\tPerhaps you should ask your mom for more recipes and add them here!");
        System.out.println(LINE);
    }

    /**
     * Display list of ingredients that match the user's parameters.
     *
     * @param list ArrayList of ingredients to be displayed.
     * @param listCount Integer representing total number of ingredients in <code>list</code>.
     */
    public void printFindIngred(ArrayList<Ingredient> list, int listCount) {
        System.out.println(LINE);
        System.out.println("\tHere's everything that I've found so far:");
        printIngredListWithOrder(list, listCount);
        System.out.println("\tTotal: " + listCount + " ingredients found!");
        System.out.println(LINE);
    }

    /**
     * Custom message when no ingredient is found with FindIngredCommand
     */
    public void printEmptyFindIngred() {
        System.out.println(LINE);
        System.out.println("\tSorry I didn't find anything! :(");
        System.out.println("\tNow go fill your fridge and come back anytime!");
        System.out.println(LINE);
    }

    /**
     * Display list of ingredients with order of each ingredient.
     *
     * @param list ArrayList of ingredients to be displayed.
     * @param listCount Integer representing total number of ingredients in <code>list</code>.
     */
    private void printIngredListWithOrder(ArrayList<Ingredient> list, int listCount) {
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
    }

    /**
     * Display an edited recipe.
     *
     * @param recipeName Name of recipe that has been edited.
     * @param editedRecipe Recipe that has been edited.
     */
    public void printEditedRecipe(String recipeName, Recipe editedRecipe) {
        System.out.println(LINE);
        System.out.println("\tYou got it boss, I have edited your recipe for " + recipeName + " to:");
        System.out.println("\t" +  editedRecipe);
        System.out.println(LINE);
    }

    /**
     * Display a random recipe.
     *
     * @param randomRecipe Recipe that has been randomly chosen.
     */
    public void printRandomRecipe(Recipe randomRecipe) {
        System.out.println(LINE);
        System.out.println("\tYou want me to call you a random recipe?");
        System.out.println("\t...");
        System.out.println("\tYou need to call it. I can't call it for you.");
        System.out.println("\tIt wouldn't be fair. It wouldn't be right.");
        System.out.println("\tIt's either one or another, and you have to say. Call it.");
        System.out.println("\t...");
        System.out.println("\tOkay who am I kidding, this is still a country for old men.");
        System.out.println("\tI will call it for you, here's your random recipe:");
        System.out.println("\t" +  randomRecipe);
        System.out.println(LINE);
    }

    /**
     * Display a list of recommended recipes to the user.
     * Information about what percentage of the recipe's ingredients that the user has,
     * and what are the missing ingredients are also displayed.
     *
     * @param recommendList List of recipes to be recommended
     */
    public void printRecommendedRecipes(ArrayList<RecommendedRecipe> recommendList) {
        if (!recommendList.isEmpty()) {
            System.out.println(LINE);
            System.out.println("\tAlright, here are my recommendations:");
            for (int i = 0; i < recommendList.size(); i++) {
                System.out.println(recommendList.get(i));
            }
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("\tUnfortunately I can't recommend you any recipes, ");
            System.out.println("\tbecause you lack the ingredients for any recipes in my database.");
            System.out.println("\tPerhaps you should hit up the grocery store.");
            System.out.println("\tIt will do you some good to go outside once in a while.");
            System.out.println(LINE);
        }
    }

    /**
     * Retrieves the line separator used to format outputs in the UI.
     *
     * @return A string representing the line separator.
     */
    public String getLine() {
        return LINE;
    }

    /**
     * Retrieves a list of all available commands and their usage instructions.
     * Used in testing of HelpCommand
     *
     * @return A string containing all available commands and their descriptions.
     */
    public String getListOfCommands() {
        return listOfCommands;
    }
}
