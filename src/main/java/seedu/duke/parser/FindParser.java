package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.FindPatientCommand;
import seedu.duke.commands.FindTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.parserutils.Find;

public class FindParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            String toFind = new Find().extract(line);
            return new FindPatientCommand(toFind);
        } else {
            String toFind = new Find().extract(line);
            return new FindTaskCommand(toFind);
        }
    }
}
