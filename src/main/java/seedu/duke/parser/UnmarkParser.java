package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.UnmarkTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import static java.lang.Integer.parseInt;

public class UnmarkParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if(state.getState() == StateType.TASK_STATE){
            String[] parts = line.split(" ");
            return new UnmarkTaskCommand(parseInt(parts[1]));
        }
        return null;
    }
}
