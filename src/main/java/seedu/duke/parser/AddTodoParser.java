package seedu.duke.parser;

import seedu.duke.commands.AddTaskCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class AddTodoParser implements CommandParser {
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.TASK_STATE) {
            String taskName = line.substring("todo ".length(), line.indexOf("/ "));
            String tag = line.substring(line.indexOf("/") + "/".length());
            return new AddTaskCommand(taskName);
        }
        return null;
    }
}
