package seedu.manager.parser;

import seedu.manager.command.Command;
import seedu.manager.command.AddCommand;
import seedu.manager.command.RemoveCommand;
import seedu.manager.command.ExitCommand;
import seedu.manager.command.MenuCommand;
import seedu.manager.command.EchoCommand;
import seedu.manager.command.ListCommand;

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
        String commandFlag = commandParts[1];

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return parseAddCommand(command, commandFlag);
        case RemoveCommand.COMMAND_WORD:
            return parseRemoveCommand(commandParts);
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
     * Parses the input string to create an {@link Command} based on the provided command flag.
     *
     * <p>
     * If the command flag is {@code "-e"}, it splits the input string into parts
     * to create an {@link AddCommand} for adding an event. If the command flag is
     * {@code "-p"}, it creates an {@link AddCommand} for adding a participant to
     * an event. If neither flag is matched, it returns an {@link EchoCommand}
     * with the original input.
     * </p>
     *
     * @param input      the input string containing the command details.
     * @param commandFlag the flag indicating the type of command to parse
     *                    (e.g., {@code "-e"} for event, {@code "-p"} for participant).
     * @return a {@link Command} object representing the parsed command.
     */
    public Command parseAddCommand(String input, String commandFlag) {
        String[] commandParts;

        if (commandFlag.equals("-e")) {
            commandParts = input.split("(-e|-t|-v)");
            return new AddCommand(commandParts[1], commandParts[2], commandParts[3]);
        } else if (commandFlag.equals("-p")) {
            commandParts = input.split("(-p|-e)");
            return new AddCommand(commandParts[1], commandParts[2]);
        }

        return new EchoCommand(input);
    }

    /**
     * Parses the 'remove' command and its arguments.
     *
     * @param commandParts The split command input from the user.
     * @return The RemoveCommand object with parsed event details.
     */
    private Command parseRemoveCommand(String[] commandParts) {
        Map<String, String> parameters = new HashMap<>();
        String currentOption = null;

        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("-")) {
                currentOption = part;
            } else if (currentOption != null) {
                parameters.put(currentOption, part);
                currentOption = null;
            }
        }

        // Extract event details from the parsed parameters
        String eventName = parameters.get("-e"); // Must be present

        return new RemoveCommand(eventName);
    }
}
