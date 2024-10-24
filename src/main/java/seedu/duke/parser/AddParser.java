package seedu.duke.parser;

import seedu.duke.commands.*;
import seedu.duke.data.state.StateType;
import seedu.duke.data.state.State;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        String trim = line.substring("add ".length()).trim();
        if (state.getState() == StateType.MAIN_STATE) {
                String patientName = trim;
                return new AddPatientCommand(patientName);
            } else if (state.getState() == StateType.TASK_STATE) {
                String taskName = trim;
                return new AddTaskCommand(taskName);
            }
        return null;
    }
}
