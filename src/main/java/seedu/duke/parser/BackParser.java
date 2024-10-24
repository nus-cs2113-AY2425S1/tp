package seedu.duke.parser;

import seedu.duke.commands.BackCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.state.State;

public class BackParser implements CommandParser {
    @Override
    public Command execute(String line, State state) {
        return new BackCommand(state); // Pass the global State object created in main to backcommand
    }
}
