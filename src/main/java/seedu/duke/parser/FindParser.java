package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class FindParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            String toFind = line.substring("find ".length());
            return null; // to add PatientFindCommand method here
        } else if (state.getState() == StateType.TASK_STATE) {
            String toFind = line.substring("find ".length());
            return null; // to add TaskFindCommand method here
        }
        return null;
    }
}
