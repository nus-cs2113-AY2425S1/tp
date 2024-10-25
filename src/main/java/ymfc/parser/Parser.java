package ymfc.parser;

import ymfc.commands.Command;
import ymfc.commands.AddRecipeCommand;
import ymfc.commands.AddIngredientCommand;
import ymfc.commands.ByeCommand;
import ymfc.commands.DeleteCommand;
import ymfc.commands.EditCommand;
import ymfc.commands.FindCommand;
import ymfc.commands.HelpCommand;
import ymfc.commands.ListCommand;
import ymfc.commands.ListIngredientsCommand;
import ymfc.commands.SortCommand;

import ymfc.exception.EmptyListException;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;

import ymfc.ingredient.Ingredient;
import ymfc.list.RecipeList;
import ymfc.recipe.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static Command parseCommand(String commandString, RecipeList recipes)
            throws InvalidCommandException, InvalidArgumentException, EmptyListException {
        Matcher m = GENERIC_FORMAT.matcher(commandString);
        if (!m.matches()) {
            throw new InvalidCommandException("Invalid command: " + commandString);
        }

        String command = m.group("command");
        String args = m.group("args") == null ? "" : m.group("args").trim();

        int numRecipes = recipes.getCounter();

        switch (command) {
        case "add":
            return getAddRecipeCommand(args);
        case "delete":
            if (numRecipes <= 0) {
                throw new EmptyListException("You can't remove something from nothing!");
            }
            return getDeleteCommand(args);
        case "listR":
            if (numRecipes <= 0) {
                throw new EmptyListException("Your recipe list is empty!");
            }
            return new ListCommand();
        case "listI":
            return new ListIngredientsCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "sort":
            if (numRecipes <= 0) {
                throw new EmptyListException("There is nothing to sort!");
            }
            return getSortCommand(args);
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
        default:
            throw new InvalidCommandException("Invalid command: " + command + "\ntype \"help\" for assistance");
        }
    }

    private static AddIngredientCommand getAddIngredientCommand(String args) throws InvalidArgumentException {
        final Pattern addIngredientCommandFormat = Pattern.compile("(?<name>[nN]/[^/]+)");
        String input = args.trim();
        Matcher m = addIngredientCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + AddRecipeCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // n/ or N/ are 2 chars
        return new AddIngredientCommand(new Ingredient(name));
    }

    /**
     * Parser for {@link AddRecipeCommand <code>AddRecipeCommand</code>}
     * @param args List of arguments as <code>String</code>
     * @return <code>AddRecipeCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static AddRecipeCommand getAddRecipeCommand(String args) throws InvalidArgumentException {

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
                        + "(\\s+(?<time>[tT]/[0-9]+))?");

        String input = args.trim();
        Matcher m = addRecipeCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + AddRecipeCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // n/ or N/ are 2 chars
        String ingredString = m.group("ingreds");
        String stepString = m.group("steps");
        ArrayList<String> ingreds = Arrays.stream(ingredString.split("\\s+[iI]/"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> steps = Arrays.stream(stepString.split("\\s+[sS][0-9]+/"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

        String cuisine = m.group("cuisine") != null ? m.group("cuisine").trim().substring(2) : null;
        Integer timeTaken = getTimeTakenInteger(m);

        return new AddRecipeCommand(new Recipe(name, ingreds, steps, cuisine, timeTaken));
    }

    private static Integer getTimeTakenInteger(Matcher m) throws InvalidArgumentException {
        String timeTakenString = m.group("time");

        Integer timeTaken = null;

        if (timeTakenString != null) {
            try {
                timeTaken = Integer.parseInt(timeTakenString.trim().substring(2));
                if (timeTaken <= 0) {
                    throw new InvalidArgumentException("Invalid time: " + timeTakenString);
                }
            } catch (NumberFormatException exception) {
                throw new InvalidArgumentException("Invalid time: " + timeTakenString);
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
        return new DeleteCommand(name);
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
        final Pattern editCommandFormat =
                // <e or E>/<String without forward slash>
                Pattern.compile("(?<name>[eE]/[^/]+)"
                        // Match ingredients: at least one i/ or I/ followed by any characters except '/'
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)"
                        // Match steps: at least one sX/ or SX/ (X is a number) followed by any characters except '/'
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)"
                        // Match optional cuisine: c/ or C/ followed by any characters except '/'
                        + "(\\s+(?<cuisine>[cC]/[^/]+))?"
                        // Match optional time taken: t/ or T/ followed by digits
                        + "(\\s+(?<time>[tT]/[0-9]+))?");

        String input = args.trim();
        Matcher m = editCommandFormat.matcher(input);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + input + "\n" + EditCommand.USAGE_EXAMPLE);
        }

        String name = m.group("name").trim().substring(2); // e/ or E/ are 2 chars
        String ingredString = m.group("ingreds");
        String stepString = m.group("steps");
        ArrayList<String> ingreds = Arrays.stream(ingredString.split("\\s+[iI]/"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> steps = Arrays.stream(stepString.split("\\s+[sS][0-9]+/"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

        String cuisine = m.group("cuisine") != null ? m.group("cuisine").trim().substring(2) : null;
        Integer timeTaken = getTimeTakenInteger(m);

        return new EditCommand(new Recipe(name, ingreds, steps, cuisine, timeTaken));

    }

    private static Command getFindCommand(String args) throws InvalidArgumentException {
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

        String query = m.group("query");
        String options = m.group("options");
        if (options == null) {
            return new FindCommand(query); // Default case, no option provided
        }

        options = options.trim();
        return new FindCommand(query,
                options.contains("n") | options.contains("n"),
                options.contains("i") | options.contains("I"),
                options.contains("s") | options.contains("S")
        );
    }
}
