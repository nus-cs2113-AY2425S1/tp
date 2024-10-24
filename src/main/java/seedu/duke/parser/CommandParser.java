package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.data.state.State;


public interface CommandParser {
    Command execute(String line, State state);
}
