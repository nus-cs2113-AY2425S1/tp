package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.MarkTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import static java.lang.Integer.parseInt;

public class MarkParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if(state.getState() == StateType.TASK_STATE) {
            String[] parts = line.split(" ");
            return new MarkTaskCommand(parseInt(parts[1]));
        }
        return null;
    }
}
