package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.data.state.State;
/**
 * Parses and executes the "help" command to return to the previous state in the application.
 * Implements the {@link CommandParser} interface.
 */
public class HelpParser implements CommandParser {
    /**
     * Executes the "help" command by creating a new {@link BackCommand} with the current state.
     *
     * @param line  The input string containing the "help" command (not utilized in execution).
     * @param state The current state of the application, which is passed to the {@link BackCommand}.
     * @return A new {@link BackCommand} initialized with the current state.
     */
    @Override
    public Command execute(String line, State state) {
        return new HelpCommand(state);
    }
}
