package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class AddDeadlineParser implements CommandParser {

    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.TASK_STATE) {
            String taskName = line.substring("deadline ".length(), line.indexOf("/by "));
            String by = line.substring(line.indexOf("/by ") + "/by ".length(), line.indexOf("/"));
            String tag = line.substring(line.indexOf("/") + "/".length());
            return null; // to add DeadlineCommand method here
        }
        return null;
    }
}
