package seedu.duke.parser;

import seedu.duke.commands.AddPatientCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.state.StateType;
import seedu.duke.data.state.State;

public class AddParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            String patientName = line.substring("add ".length());
            return new AddPatientCommand(patientName);
        }
        return null;
    }
}
