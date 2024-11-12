package ymfc.parser;

import ymfc.commands.Command;
import ymfc.commands.AddIngredientCommand;
import ymfc.commands.AddCommand;
import ymfc.commands.ByeCommand;
import ymfc.commands.DeleteCommand;
import ymfc.commands.DeleteIngredientCommand;
import ymfc.commands.EditCommand;
import ymfc.commands.FindCommand;
import ymfc.commands.FindIngredientCommand;
import ymfc.commands.HelpCommand;
import ymfc.commands.ListCommand;
import ymfc.commands.ListIngredientCommand;
import ymfc.commands.RandomCommand;
import ymfc.commands.RecommendCommand;
import ymfc.commands.SortCommand;
import ymfc.commands.SortIngredientCommand;

import ymfc.exception.EmptyListException;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;

import ymfc.exception.InvalidSaveLineException;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.recipe.Recipe;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parse user input commands
 */
public final class Parser {
    // First word <command> before the first white space, then all the arguments <args>
    static final Pattern GENERIC_FORMAT = Pattern.compile("(?<command>\\S+)(\\s+(?<args>.*))?");

    /**
     * Parses user input commands and return a <code>Command</code> object
     * @param commandString Input command as <code>String</code>
     * @return <code>Command</code> object based on input command
     * @throws InvalidCommandException If command cannot be parsed
     * @throws InvalidArgumentException If command can be parsed but with invalid arguments
     */
    public static Command parseCommand(String commandString, RecipeList recipes, IngredientList ingredients)
            throws InvalidCommandException, InvalidArgumentException, EmptyListException {
        Matcher m = GENERIC_FORMAT.matcher(commandString);
        if (!m.matches()) {
            throw new InvalidCommandException("Invalid command: " + commandString);
        }

        String command = m.group("command").trim();
        String args = m.group("args") == null ? "" : m.group("args").trim();

        int numRecipes = recipes.getCounter();
        int numIngredients = ingredients.getCounter();

        switch (command) {
        case "add":
            return getAddRecipeCommand(args);
        case "delete":
            if (numRecipes <= 0) {
                throw new EmptyListException("You can't remove something from nothing!");
            }
            return getDeleteCommand(args);
        case "deleteI":
            if (numIngredients <= 0) {
                throw new EmptyListException("You can't remove something from nothing!");
            }
            return getDeleteIngredientCommand(args);
        case "list":
            if (numRecipes <= 0) {
                throw new EmptyListException("Your recipe list is empty!");
            }
            return new ListCommand();
        case "listI":
            if (numIngredients <= 0) {
                throw new EmptyListException("Your ingredient list is empty!");
            }
            return new ListIngredientCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "sort":
            if (numRecipes <= 0) {
                throw new EmptyListException("There is nothing to sort!");
            }
            return getSortCommand(args);
        case "sortI":
            if (numIngredients <= 0) {
                throw new EmptyListException("There is nothing to sort!");
            }
            return new SortIngredientCommand();
        case "new":
            return getAddIngredientCommand(args);
        case "edit":
            if (numRecipes <= 0) {
                throw new EmptyListException("There is nothing to edit!");
            }
            return getEditCommand(args);
        case "find":
            if (numRecipes <= 0) {
                throw new EmptyListException("There is nothing to find!");
            }
            return getFindCommand(args);
        case "findI":
            if (numIngredients <= 0) {
                throw new EmptyListException("There are no ingredients to find!");
            }
            return getFindIngredCommand(args);
        case "random":
            if (numRecipes <= 0) {
                throw new EmptyListException("A random recipe from a pool of nothing, is nothing.");
            }
            return new RandomCommand();
        case "recommend":
            if (numRecipes <= 0) {
                throw new EmptyListException("I don't have any recipes, what do you want me to recommend?");
            }
            return new RecommendCommand();
        default:
            throw new InvalidCommandException("Invalid command: " + command + "\ntype \"help\" for assistance");
        }
    }

    /**
     * Checks if the recipe save line is of the proper format.
     * Then, interprets the recipe save line to extract out its parameters.
     *
     * @param saveRecipeString Save line from cookbook.txt to check and interpret
     * @return AddCommand object containing the proper input parameters
     * @throws InvalidSaveLineException If the save line is of an improper format
     */
    public static AddCommand parseRecipeSaveLine(String saveRecipeString) throws InvalidSaveLineException {
        Matcher m = GENERIC_FORMAT.matcher(saveRecipeString);
        if (!m.matches()) {
            throw new InvalidSaveLineException("Invalid line found in Recipe save file: "
                    + saveRecipeString + System.lineSeparator());
        }

        String command = m.group("command").trim();
        String args = m.group("args") == null ? "" : m.group("args").trim();

        if (!command.equals("add")) {
            throw new InvalidSaveLineException("Invalid line found in Recipe save file: "
                    + saveRecipeString + System.lineSeparator());
        }

        try {
            return getAddRecipeCommand(args);
        } catch (InvalidArgumentException exception) {
            throw new InvalidSaveLineException("Invalid parameters found in Recipe save file: "
                    + saveRecipeString + System.lineSeparator());
        }
    }

    /**
     * Checks if the ingredient save line is of the proper format.
     * Then, interprets the ingredient save line to extract out its parameters.
     *
     * @param saveIngredientString Save line from inventory.txt to check and interpret
     * @return AddIngredientCommand object containing the proper input parameters
     * @throws InvalidSaveLineException If the save line is of an improper format
     */
    public static AddIngredientCommand parseIngredientSaveLine(String saveIngredientString)
            throws InvalidSaveLineException {
        Matcher m = GENERIC_FORMAT.matcher(saveIngredientString);
        if (!m.matches()) {
            throw new InvalidSaveLineException("Invalid line found in Ingredient save file: "
                    + saveIngredientString + System.lineSeparator());
        }

        String command = m.group("command").trim();
        String args = m.group("args") == null ? "" : m.group("args").trim();

        if (!command.equals("new")) {
            throw new InvalidSaveLineException("Invalid line found in Ingredient save file: "
                    + saveIngredientString + System.lineSeparator());
        }

        try {
            return getAddIngredientCommand(args);
        } catch (InvalidArgumentException exception) {
            throw new InvalidSaveLineException("Invalid parameters found in Ingredient save file: "
                    + saveIngredientString + System.lineSeparator());
        }
    }


    private static AddIngredientCommand getAddIngredientCommand(String args) throws InvalidArgumentException {
        final Pattern addIngredientCommandFormat = Pattern.compile("(?<name>[nN]/[^/]+)");
        String input = args.trim();
        Matcher m = addIngredientCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input
                    + "\n" + AddIngredientCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // n/ or N/ are 2 chars
        String trimmedName = name.trim();
        return new AddIngredientCommand(new Ingredient(trimmedName));
    }

    /**
     * Parser for {@link AddCommand <code>AddRecipeCommand</code>}
     * @param args List of arguments as <code>String</code>
     * @return <code>AddRecipeCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static AddCommand getAddRecipeCommand(String args) throws InvalidArgumentException {

        final Pattern addRecipeCommandFormat =
                // <n or N>/<String without forward slash>
                Pattern.compile("(?<name>[nN]/[^/]+)"
                        // Match ingredients: at least one i/ or I/ followed by any characters except '/'
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)"
                        // Match steps: at least one sX/ or SX/ (X is a number) followed by any characters except '/'
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)"
                        // Match optional cuisine: c/ or C/ followed by any characters except '/'
                        + "(\\s+(?<cuisine>[cC]/[^/]+))?"
                        // Match optional time taken: t/ or T/ followed by digits
                        + "(\\s+(?<time>[tT]/[^/]+))?");

        String input = args.trim();
        Matcher m = addRecipeCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + AddCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // n/ or N/ are 2 chars
        String trimmedName = name.trim();
        String ingredString = m.group("ingreds");
        String stepString = m.group("steps");

        // Collect ingredients
        List<String> ingredientList = Arrays.stream(ingredString.split("\\s+[iI]/"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        // Check for duplicates
        HashSet<String> ingredientSet = new HashSet<>();
        for (String ingredient : ingredientList) {
            if (!ingredientSet.add(ingredient.toLowerCase())) {
                throw new InvalidArgumentException("Duplicate ingredient found: " + ingredient
                        + ".\nThis is not allowed!");
            }
        }

        ArrayList<Ingredient> ingreds = Arrays.stream(ingredString.split("\\s+[iI]/"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Ingredient::new)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<String> steps = Arrays.stream(stepString.split("\\s+[sS][0-9]+/"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

        // Extract step identifiers (s1, s2, ...) and validate for duplicates or missing numbers
        List<String> stepIdentifiers = Arrays.stream(stepString.split("\\s+"))
                .filter(step -> step.matches("[sS][0-9]+/.*")) // Ensure the string matches the step format
                .map(step -> step.split("/")[0]) // Extracts "s1", "s2", etc.
                .toList();

        validateStepNumbers(stepIdentifiers); // Check for missing/duplicate numbers

        String cuisineInput = m.group("cuisine");
        String cuisine = null;
        if (cuisineInput != null) {
            cuisine = cuisineInput.trim().substring(2).trim();
        }
        Integer timeTaken = getTimeTakenInteger(m);

        return new AddCommand(new Recipe(trimmedName, ingreds, steps, cuisine, timeTaken));
    }

    private static Integer getTimeTakenInteger(Matcher m) throws InvalidArgumentException {
        final int timeLimit = 1 << 16 - 1;
        String timeTakenString = m.group("time");

        Integer timeTaken = null;

        if (timeTakenString != null) {
            try {
                timeTaken = Integer.parseInt(timeTakenString.trim().substring(2).trim());
                if ((timeTaken <= 0) || (timeTaken > timeLimit)) {
                    throw new InvalidArgumentException("Invalid time: " + timeTakenString + "\n" +
                            "Time should be a positive integer less than 65536.");
                }
            } catch (NumberFormatException exception) {
                throw new InvalidArgumentException("Invalid time: " + timeTakenString + "\n" +
                        "Time should be a positive integer less than 65536.");
            }
        }
        return timeTaken;
    }

    /**
     * Parser for {@link DeleteCommand <code>DeleteCommand</code>}
     * @param args Name argument as <code>String</code>
     * @return <code>DeleteCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static DeleteCommand getDeleteCommand(String args) throws InvalidArgumentException {
        final Pattern deleteCommandFormat  =
                Pattern.compile("(?<name>[nN]/[^/]+)");
        String input = args.trim();
        Matcher m = deleteCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + DeleteCommand.USAGE_EXAMPLE);
        }
        String name = m.group("name").trim().substring(2);
        return new DeleteCommand(name.trim());
    }

    private static DeleteIngredientCommand getDeleteIngredientCommand(String args) throws InvalidArgumentException {
        final Pattern deleteIngredientCommandFormat =
                Pattern.compile("(?<name>[nN]/[^/]+)");
        String input = args.trim();
        Matcher m = deleteIngredientCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n"
                    + DeleteIngredientCommand.USAGE_EXAMPLE);
        }
        String name = m.group("name").trim().substring(2);
        return new DeleteIngredientCommand(name.trim());
    }

    private static SortCommand getSortCommand(String args) throws InvalidArgumentException {
        final Pattern sortCommandFormat  =
                Pattern.compile("(?<name>[sS]/[^/]+)");
        String input = args.trim();
        Matcher m = sortCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument: " + input + "\n" + SortCommand.USAGE_EXAMPLE);
        }
        String name = m.group("name").trim().substring(2);

        if (!name.equals("name") && !name.equals("time")) {
            throw new InvalidArgumentException("Invalid argument: " + input + "\n" + SortCommand.USAGE_EXAMPLE);
        }

        return new SortCommand(name);
    }

    /**
     * Parser for {@link EditCommand <code>EditCommand</code>}
     * @param args List of arguments as <code>String</code>
     * @return <code>EditCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static EditCommand getEditCommand(String args) throws InvalidArgumentException {
        // Pattern for an edit command, everything is optional except for the name of the recipe to be edited
        final Pattern editCommandFormat =
                // <e or E>/<String without forward slash>
                Pattern.compile("(?<matchName>[eE]/[^/]+)"
                        // Match optional new name: n/ or N/ followed by any characters except '/'
                        + "(\\s+(?<newName>[nN]/[^/]+))?"
                        // Match optional ingredients: i/ or I/ followed by any characters except '/'
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)?"
                        // Match optional steps: sX/ or SX/ (X is a number) followed by any characters except '/'
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)?"
                        // Match optional cuisine: c/ or C/ followed by any characters except '/'
                        // Accepts empty field after c/ or C/ to indicate no cuisine
                        + "(\\s+(?<cuisine>[cC]/[^/]*))?"
                        // Match optional time taken: t/ or T/ followed by digits
                        // Accepts empty field after t/ T/ to indicate no time
                        + "(\\s+(?<time>[tT]/[^/]*))?");

        String input = args.trim();
        Matcher m = editCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + EditCommand.USAGE_EXAMPLE);
        }

        // Get the name of the recipe to be edited
        String matchName = m.group("matchName").trim().substring(2).trim(); // e/ or E/ are 2 chars

        // Get optional new name to replace the existing one
        String newName = null;
        String newNameInput = m.group("newName");
        if (newNameInput != null) {
            newName = newNameInput.trim().substring(2).trim(); // e/ or E/ are 2 chars
        }

        // Get optional new ingredient list to replace the existing one
        String ingredString = m.group("ingreds");
        ArrayList<Ingredient> ingreds = null;
        if (ingredString != null) {
            //@@author gskang-22
            // Collect ingredients
            List<String> ingredientList = Arrays.stream(ingredString.split("\\s+[iI]/"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

            // Check for duplicates
            HashSet<String> ingredientSet = new HashSet<>();
            for (String ingredient : ingredientList) {
                if (!ingredientSet.add(ingredient.toLowerCase())) {
                    throw new InvalidArgumentException("Duplicate ingredient found: " + ingredient
                            + ".\nThis is not allowed!");
                }
            }
            //@@author

            ingreds = Arrays.stream(ingredString.split("\\s+[iI]/"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Ingredient::new)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        // Get optional new steps list to replace the existing one
        String stepString = m.group("steps");
        ArrayList<String> steps = null;
        if (stepString != null) {
            //@@author gskang-22
            // Extract step identifiers (s1, s2, ...) and validate for duplicates or missing numbers
            List<String> stepIdentifiers = Arrays.stream(stepString.split("\\s+"))
                    .filter(step -> step.matches("[sS][0-9]+/.*")) // Ensure the string matches the step format
                    .map(step -> step.split("/")[0]) // Extracts "s1", "s2", etc.
                    .toList();

            validateStepNumbers(stepIdentifiers); // Check for missing/duplicate numbers
            //@@author

            steps = Arrays.stream(stepString.split("\\s+[sS][0-9]+/"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        // Get optional new cuisine to replace the existing one
        String cuisineInput = m.group("cuisine");
        String cuisine = null;
        if (cuisineInput != null) {
            cuisine = cuisineInput.trim().substring(2).trim();
        }

        // Get optional new time to replace the existing one
        String timeInput = m.group("time");
        Integer timeTaken = null;
        if (timeInput != null) {
            // Remove t/ or T/ from the timeInput
            String timeInputEdited = timeInput.replaceAll("[tT]/", "");
            if (timeInputEdited.trim().isEmpty()) {
                timeTaken = -1; // Negative invalid TimeTaken to indicate deletion later on
            } else {
                timeTaken = getTimeTakenInteger(m);
            }
        }

        // If all optional parameters are null, inform the user
        if (newName == null && ingreds == null && steps == null && cuisine == null && timeTaken == null) {
            throw new InvalidArgumentException("So you are giving me nothing to edit about your recipe?"
                    + System.lineSeparator() + "Look up what \"edit\" means in the dictionary.");
        }

        return new EditCommand(matchName, newName, ingreds, steps, cuisine, timeTaken);
    }

    private static FindCommand getFindCommand(String args) throws InvalidArgumentException {
        final Pattern findCommandPattern =
                // Options to feed into findCommand, could be null
                // Length of 1 to 3, consist of only "nNiIsS", must ends with /
                Pattern.compile("(?<options>[nNiIsS]{1,3}/)?" +
                        // Query command
                        "(?<query>[^/]+)");
        String input = args.trim();
        Matcher m = findCommandPattern.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + FindCommand.USAGE_EXAMPLE);
        }

        String query = m.group("query").trim();
        String options = m.group("options");
        if (options == null) {
            return new FindCommand(query); // Default case, no option provided
        }

        String trimmedOptions = options.trim();
        int nCount = countCharOccurrence(trimmedOptions, "[^nN]");
        int iCount = countCharOccurrence(trimmedOptions, "[^iI]");
        int sCount = countCharOccurrence(trimmedOptions, "[^sS]");
        if (nCount > 1 | iCount > 1 | sCount > 1) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" +
                    "At most ONE character for each option is allowed!" + "\n" +
                    FindCommand.USAGE_EXAMPLE);
        }

        return new FindCommand(query,
                nCount == 1, // Can only be 1 or 0
                iCount == 1,
                sCount == 1
        );
    }

    private static Command getFindIngredCommand(String args) throws InvalidArgumentException {
        final Pattern findCommandPattern = Pattern.compile("^(?<query>.+)$");

        String input = args.trim();
        Matcher m = findCommandPattern.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" +
                    FindIngredientCommand.USAGE_EXAMPLE);
        }

        String query = m.group("query").trim();
        return new FindIngredientCommand(query); // No options, only the query is needed
    }

    private static int countCharOccurrence(String options, String regex) {
        return options.replaceAll(regex, "").length();
    }

    public static void validateStepNumbers(List<String> stepStrings) throws InvalidArgumentException {
        if (stepStrings.isEmpty()) {
            throw new InvalidArgumentException("No steps provided. Please provide at least one step.");
        }

        ArrayList<Integer> stepNumbers = new ArrayList<>();
        int maxStepNumber = 0;

        // Parse step numbers and check for duplicates
        for (String step : stepStrings) {
            // Extract the step number (e.g., "s1" -> 1)
            try {
                int stepNumber = Integer.parseInt(step.split("/")[0].substring(1));
                if (stepNumbers.contains(stepNumber)) {
                    throw new InvalidArgumentException("Duplicate step number found: s" + stepNumber
                            + "\nLearn to count!");
                }
                if (stepNumber == 0) {
                    throw new InvalidArgumentException("Invalid step number s0 found. \nStep numbers start from s1!");
                }
                stepNumbers.add(stepNumber);
                maxStepNumber = Math.max(maxStepNumber, stepNumber);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                throw new InvalidArgumentException("Invalid step format. Expected format: s1, s2, ...");
            }
        }

        // Check for missing numbers in the range 1 to maxStepNumber
        for (int i = 1; i <= maxStepNumber; i++) {
            if (!stepNumbers.contains(i)) {
                throw new InvalidArgumentException("Missing step number: s" + i
                        + "\nLearn to count!");
            }
        }

        // Verify the steps are in strict sequential order
        for (int i = 0; i < stepNumbers.size(); i++) {
            if (stepNumbers.get(i) != i + 1) {  // Steps should be exactly 1, 2, 3, ..., n
                throw new InvalidArgumentException("Steps are not in order. Arrange them sequentially " +
                        "(s1, s2, s3, ...)!");
            }
        }
    }

}
