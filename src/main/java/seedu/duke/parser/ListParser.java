package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListPatientCommand;
import seedu.duke.commands.ListTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class ListParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            return new ListPatientCommand();
        } else if (state.getState() == StateType.TASK_STATE) {
            return new ListTaskCommand();
        }

        return null;
    }
}
