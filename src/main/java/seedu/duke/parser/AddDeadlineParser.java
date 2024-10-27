package seedu.duke.parser;

import seedu.duke.commands.AddTaskCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.parserutils.Duration;
import seedu.duke.parser.parserutils.TaskName;
import seedu.duke.parser.parserutils.Tag;

public class AddDeadlineParser implements CommandParser {

    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.TASK_STATE) {
            String taskName = new TaskName().extract(line);
            String by = new Duration().extract(line);
            String tag = new Tag().extract(line);
            return new AddTaskCommand("deadline", taskName, by, tag);
        }
        return null;
    }
}
