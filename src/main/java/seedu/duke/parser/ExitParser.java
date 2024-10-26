package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.data.state.State;

/**
 * Parses the exit command.
 */
public class ExitParser implements CommandParser {
    @Override
    public Command execute(String line, State state) {
        return new ExitCommand();
    }
}
