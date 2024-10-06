package seedu.manager.parser;

import seedu.manager.command.Command;
import seedu.manager.command.EchoCommand;
import seedu.manager.command.ExitCommand;

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
        String[] commandParts = command.split(" ", 2);
        String commandWord = commandParts[0];

        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new EchoCommand(command);
        }
    }
}
