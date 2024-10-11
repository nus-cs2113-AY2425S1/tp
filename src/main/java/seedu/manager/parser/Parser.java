package seedu.manager.parser;

import seedu.manager.command.Command;
import seedu.manager.command.AddCommand;
import seedu.manager.command.ExitCommand;
import seedu.manager.command.MenuCommand;
import seedu.manager.command.EchoCommand;
import seedu.manager.command.ListCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the command parser for EventManagerCLI
 */
public class Parser {

    /**
     * Returns a command based on the given user command string
     *
     * @param command The given command string from the user
     */
    public Command parseCommand(String command){
        String[] commandParts = command.split(" ");
        String commandWord = commandParts[0];

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return parseAddCommand(commandParts);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case MenuCommand.COMMAND_WORD:
            return new MenuCommand();

        default:
            return new EchoCommand(command);
        }
    }

    /**
     * Parses the 'add' command and its arguments.
     *
     * @param commandParts The split command input from the user.
     * @return The AddCommand object with parsed event details.
     */
    public Command parseAddCommand(String[] commandParts) {
        // Map to store the option-value pairs
        Map<String, String> parameters = new HashMap<>();
        String currentOption = null;

        // Parse the options and their values
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("-")) {
                currentOption = part;  // Current part is an option (e.g., -e, -time, -venue)
            } else if (currentOption != null) {
                parameters.put(currentOption, part);  // Store the value for the current option
                currentOption = null;  // Reset after assigning value
            }
        }

        // Extract event details from the parsed parameters
        String eventName = parameters.get("-e");
        String time = parameters.get("-time");
        String venue = parameters.get("-venue");

        // Create and return the AddCommand with parsed parameters
        return new AddCommand(eventName, time, venue);
    }
}
