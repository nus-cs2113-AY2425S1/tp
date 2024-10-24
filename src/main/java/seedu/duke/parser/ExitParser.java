package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.data.state.State;

public class ExitParser implements CommandParser {
    @Override
    public Command execute(String line, State state) {
        System.out.println("Exiting program...");
        System.exit(0); // Terminates the program gracefully
        return null;
    }
}
