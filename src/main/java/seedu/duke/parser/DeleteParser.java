package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeletePatientCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.parserutils.Index;

import static java.lang.Integer.parseInt;

public class DeleteParser implements CommandParser {

    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            int id = parseInt(new Index().extract(line));
            return new DeletePatientCommand(id);
        } else if (state.getState() == StateType.TASK_STATE) {
            int id = parseInt(new Index().extract(line));
            return new DeletePatientCommand(id);
        }
        return null;
    }
}
