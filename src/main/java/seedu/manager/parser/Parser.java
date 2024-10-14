package seedu.manager.parser;

import seedu.manager.command.Command;
import seedu.manager.command.AddCommand;
import seedu.manager.command.RemoveCommand;
import seedu.manager.command.ExitCommand;
import seedu.manager.command.MenuCommand;
import seedu.manager.command.EchoCommand;
import seedu.manager.command.ListCommand;
import seedu.manager.command.ViewCommand;

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
            return parseAddCommand(command, commandParts);
        case RemoveCommand.COMMAND_WORD:
            return parseRemoveCommand(command, commandParts);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ViewCommand.COMMAND_WORD:
            return parseViewCommand(command, commandParts);
        case MenuCommand.COMMAND_WORD:
            return new MenuCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new EchoCommand(command);
        }
    }

    /**
     * Parses the input string to create an {@link Command} based on the provided command parts.
     *
     * <p>
     * This method checks the command flag extracted from the command parts. If the command
     * flag is {@code "-e"}, it splits the input string into parts to create an
     * {@link AddCommand} for adding an event. If the command flag is {@code "-p"},
     * it creates an {@link AddCommand} for adding a participant to an event. If neither
     * flag is matched, it returns an {@link EchoCommand} with the original input.
     * </p>
     *
     * @param input        the input string containing the command details.
     * @param commandParts an array of strings representing the parsed command parts,
     *                     where the second element is the command flag.
     * @return a {@link Command} object representing the parsed command.
     */
    public Command parseAddCommand(String input, String[] commandParts) {
        String commandFlag = commandParts[1];
        String[] inputParts;

        if (commandFlag.equals("-e")) {
            inputParts = input.split("(-e|-t|-v)");
            return new AddCommand(inputParts[1], inputParts[2], inputParts[3]);
        } else if (commandFlag.equals("-p")) {
            inputParts = input.split("(-p|-e)");
            return new AddCommand(inputParts[1], inputParts[2]);
        }

        return new EchoCommand(input);
    }

    /**
     * Parses the input string to create a {@link Command} based on the provided command parts.
     *
     * <p>
     * This method checks the command flag extracted from the command parts. If the command
     * flag is {@code "-e"}, it splits the input string to create a {@link RemoveCommand}
     * for removing an event. If the command flag is {@code "-p"}, it creates a
     * {@link RemoveCommand} for removing a participant from an event. If neither flag
     * is matched, it returns an {@link EchoCommand} with the original input.
     * </p>
     *
     * @param input        the input string containing the command details.
     * @param commandParts an array of strings representing the parsed command parts,
     *                     where the second element is the command flag.
     * @return a {@link Command} object representing the parsed command.
     */
    private Command parseRemoveCommand(String input, String[] commandParts) {
        String commandFlag = commandParts[1];
        String[] inputParts;

        if (commandFlag.equals("-e")) {
            inputParts = input.split("-e");
            return new RemoveCommand(inputParts[1]);
        } else if (commandFlag.equals("-p")) {
            inputParts = input.split("(-p|-e)");
            return new RemoveCommand(inputParts[1], inputParts[2]);
        }

        return new EchoCommand(input);
    }

    /**
     * Parses the input string to create a {@link Command} based on the provided command parts.
     *
     * <p>
     * This method checks the command flag extracted from the command parts. If the command
     * flag is {@code "-e"}, it splits the input string to create a {@link ViewCommand}
     * for viewing the participants in the event.
     * Otherwise, it returns an {@link EchoCommand} with the original input.
     * </p>
     *
     * @param input        the input string containing the command details.
     * @param commandParts an array of strings representing the parsed command parts,
     *                     where the second element is the command flag.
     * @return a {@link Command} object representing the parsed command.
     */
    private Command parseViewCommand(String input, String[] commandParts) {
        String commandFlag = commandParts[1];

        if (commandFlag.equals("-e")) {
            String [] inputParts = input.split("-e");
            return new  ViewCommand(inputParts[1].trim());
        }

        return new EchoCommand(input);
    }
}
