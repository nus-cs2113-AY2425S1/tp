package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.SelectPatientCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.parserutils.Index;

import static java.lang.Integer.parseInt;

public class SelectParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if(state.getState() == StateType.MAIN_STATE){
            int id = parseInt(new Index().extract(line));
            return new SelectPatientCommand(id, state);
        }
        return null;
    }
}
