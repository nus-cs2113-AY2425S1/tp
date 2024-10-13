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

public interface Parser {
    final static Pattern GENERIC_FORMAT = Pattern.compile("(?<command>\\S+)\\s+(?<args>.*)");
    final static Pattern ARGUMENTS_FORMAT = Pattern.compile("(?<args>[a-zA-Z][0-9]?/.*)*");
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

    private static Command getAddRecipeCommand(String args) throws InvalidArgumentException {
        final Pattern ADD_RECIPE_COMMAND_FORMAT =
                Pattern.compile("(?<name>[nN]/[^/]+)"
                        + "(?<ingreds>(\\s+[iI]/[^/]+)+)"
                        + "(?<steps>(\\s+[sS][0-9]+/[^/]+)+)");
        args = args.trim();
        Matcher m = ADD_RECIPE_COMMAND_FORMAT.matcher(args);
        if (!m.matches()) {
            throw new InvalidArgumentException("Invalid argument(s): " + args);
        }

        String name = m.group("name").trim().substring(2);
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

    private static Command getDeleteCommand(String args) throws InvalidArgumentException {
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
