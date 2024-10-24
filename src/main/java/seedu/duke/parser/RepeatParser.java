package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class RepeatParser implements CommandParser {
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.TASK_STATE) {
            String taskName = line.substring("deadline ".length(), line.indexOf("/every "));
            String recur = line.substring(line.indexOf("/every ") + "/every ".length(), line.indexOf("/every"));
            String tag = line.substring(line.indexOf("/") + "/".length());
            return null; // to add DeadlineCommand method here
        }
        return null;
    }
}
