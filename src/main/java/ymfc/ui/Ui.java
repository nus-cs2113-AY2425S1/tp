package ymfc.ui;

import ymfc.ingredient.Ingredient;
import ymfc.recipe.Recipe;

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class representing the user interface for interacting with the user.
 */
public class Ui {
    private final String LINE = "\t_____________________________________________________________________________";
    private final String LOGO = "                 (\\" + System.lineSeparator() +
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
            "This is all the things you can do with me:\n"
            + "0. help                              -  Lists out all the available commands and their usage\n"
            + LINE
            + "The following commands is for working with your recipes list\n"
            + "1. list                              -  Lists out all the existing recipes\n"
            + "2. add n/name i/ingredients s1/step  -  Adds a new recipe.\n"
            + "\t add more ingredients using more i/ tags\n"
            + "\t add more steps using more sx/ tags, where x starts from 1 and increases one by one\n"
            + "3. sort s/name                       -  Sorts the recipes alphabetically by name\n"
            + "4. sort s/time                       -  Sorts the recipes by time, from least to most\n"
            + "5. find KEYWORDS                     -  Finds all recipes that \n"
            + "\t find name             = find in name\n"
            + "\t find i/keyword        = find in ingredients\n"
            + "\t find ns/keyword       = find in name or steps\n"
            + "\t find nis/keyword      = find in name or ingredients or steps\n"
            + "6. edit e/name i/ingredients s1/step -  Edit an existing recipe's steps and ingredients\n"
            + "\t add more ingredients using more i/ tags\n"
            + "\t add more steps using more sx/ tags, where x starts from 1 and increases one by one\n"
            + "9. bye                               -  Ends the program\n"
            + LINE
            + "The following commands is for working with your ingredients list\n"
            + "1. new n/ingredient                  - Add a new ingredient to your list\n"
            //+ "2. list                            - Lists out all your current ingredients\n"
            //+ "3. delete n/ingredient                  - Delete an existing ingredient from your list\n"
            + LINE
            + "Got it? Let's get back to cooking.\n";

    private Scanner userInput;

    /**
     * Constructor for a <code>YMFC.Ui</code> object.
     * Overloads default constructor to initialise a scanner object reading user inputs.
     *
     * @param input InputStream for the Ui class to read commands from
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
     * @return String representing what user typed into the program
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
     * Display a specified message.
     *
     * @param input String array representing the message, each element representing one line
     */
    public void printMessage(String[] input) {
        System.out.println(LINE);
        for (String line: input) {
            System.out.println("\t" + line);
        }
        System.out.println(LINE);
    }

    public void printErrorMessage(String error) {
        System.out.println(error);
    }

    /**
     * Display a newly added recipe.
     *
     * @param addedRecipe Recipe that has been added
     * @param listCount Number of recipes currently in the list
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
     * Display the entire list of recipe.
     *
     * @param list ArrayList of recipes to be displayed
     * @param listCount Integer representing total number of recipes in <code>list</code>
     */
    public void printList(ArrayList<Recipe> list, int listCount) {
        System.out.println(LINE);
        System.out.println("\tHere's everything in my collection so far:");
        printListWithOrder(list, listCount);
        System.out.println(LINE);
    }

    /**
     * Display list of recipes with order of each recipe
     * @param list ArrayList of recipes to be displayed
     * @param listCount Integer representing total number of recipes in <code>list</code>
     */
    private void printListWithOrder(ArrayList<Recipe> list, int listCount) {
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
            System.out.println(LINE);
        }
    }

    public void printIngredientList(ArrayList<Ingredient> list, int listCount) {
        System.out.println(LINE);
        System.out.println("\tHere's all the ingredients you currently have: ");
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
            System.out.println(LINE);
        }
        System.out.println(LINE);
    }

    /**
     * Display a recipe that been deleted.
     *
     * @param deletedRecipe Recipe that has been deleted
     * @param listCount Integer representing number of recipes in the list currently
     */
    public void printDeletedTask(String deletedRecipe, int listCount) {
        System.out.println(LINE);
        System.out.println("\tAww, I shall begrudgingly let go of this recipe:");
        System.out.println("\t  " + deletedRecipe);
        // Conditional operator to pluralize "recipe" when listCount above 1
        System.out.println("\tYou currently have " + listCount +
                (listCount <= 1 ? " recipe" : " recipes") + " in your list.");
        System.out.println(LINE);
    }

    public void printHelp() {
        System.out.println(LINE);
        System.out.println(listOfCommands);
        System.out.println(LINE);
    }

    public void printFind(ArrayList<Recipe> list, int listCount) {
        System.out.println(LINE);
        System.out.println("\tHere's everything that I've found so far:");
        printListWithOrder(list, listCount);
        System.out.println("\tTotal: " + listCount + " recipes found!");
        System.out.println(LINE);
    }
}
