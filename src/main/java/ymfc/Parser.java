package ymfc;

import ymfc.commands.*;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;
import ymfc.recipe.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parse user input commands
 */
public interface Parser {
    // First word <command> before the first white space, then all the arguments <args>
    final static Pattern GENERIC_FORMAT = Pattern.compile("(?<command>\\S+)\\s+(?<args>.*)");
    // final static Pattern ARGUMENTS_FORMAT = Pattern.compile("(?<args>[a-zA-Z][0-9]?/.*)*");

    /**
     * Parses user input commands and return a <code>Command</code> object
     * @param commandString Input command as <code>String</code>
     * @return <code>Command</code> object based on input command
     * @throws InvalidCommandException If command cannot be parsed
     * @throws InvalidArgumentException If command can be parsed but with invalid arguments
     */
    static Command parseCommand(String commandString) throws InvalidCommandException, InvalidArgumentException {
        Matcher m = GENERIC_FORMAT.matcher(commandString);
        if (!m.matches()) {
            throw new InvalidCommandException("Invalid command: " + commandString);
        }

        String command = m.group("command");
        String args = m.group("args");
        switch (command) {
        case "add":
            return getAddRecipeCommand(args);
        case "delete":
            return getDeleteCommand(args);
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new InvalidCommandException("Invalid command: " + command);
        }
    }

    /**
     * Parser for {@link AddRecipeCommand <code>AddRecipeCommand</code>}
     * @param args List of arguments as <code>String</code>
     * @return <code>AddRecipeCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static AddRecipeCommand getAddRecipeCommand(String args) throws InvalidArgumentException {
        final Pattern ADD_RECIPE_COMMAND_FORMAT =
                Pattern.compile("(?<name>[nN]/[^/]+)"
                        // <n or N>/<String without forward slash>
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)"
                        // (<at least 1 whitespace><i or I>/<String without forward slash>) at least once
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)");
                        // (<at least 1 whitespace><s or S><Number>/<String without forward slash>) at least once
        args = args.trim();
        Matcher m = ADD_RECIPE_COMMAND_FORMAT.matcher(args);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + args);
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
        return new AddRecipeCommand(new Recipe(name, ingreds, steps));
    }

    /**
     * Parser for {@link DeleteCommand <code>DeleteCommand</code>}
     * @param args Name argument as <code>String</code>
     * @return <code>DeleteCommand</code> object, waiting to be executed
     * @throws InvalidArgumentException If invalid format of arguments is found
     */
    private static DeleteCommand getDeleteCommand(String args) throws InvalidArgumentException {
        final Pattern DELETE_COMMAND_FORMAT =
                Pattern.compile("(?<name>[nN]/[^/]+)");
        args = args.trim();
        Matcher m = DELETE_COMMAND_FORMAT.matcher(args);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + args);
        }
        String name = m.group("name").trim().substring(2);
        return new DeleteCommand(0);
//        return new DeleteCommand(name);
    }


}
