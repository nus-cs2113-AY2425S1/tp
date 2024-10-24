package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.SelectPatientCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import static java.lang.Integer.parseInt;

public class SelectParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        String[] parts = line.split(" ");
        if(state.getState() == StateType.MAIN_STATE){
            return new SelectPatientCommand(parseInt(parts[1]), state);
        }
        return null;
    }
}
