package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeletePatientCommand;
import seedu.duke.commands.DeleteTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class DeleteParser implements CommandParser {
    private static final Logger logger = Logger.getLogger("Parser");

    @Override
    public Command execute(String line, State state) {
        String[] parts = line.split(" ");
        if (state.getState() == StateType.MAIN_STATE) {
            return new DeletePatientCommand(parseInt(parts[1]));
        } else if (state.getState() == StateType.TASK_STATE) {
            return new DeleteTaskCommand(parseInt(parts[1]));
        }

        return null;
    }

}
