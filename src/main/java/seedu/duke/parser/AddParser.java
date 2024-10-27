package seedu.duke.parser;

import seedu.duke.commands.AddPatientCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.state.StateType;
import seedu.duke.data.state.State;
import seedu.duke.parser.parserUtils.PatientName;
import seedu.duke.parser.parserUtils.TaskName;
import seedu.duke.parser.parserUtils.Tag;

public class AddParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            String patientName = new PatientName().extract(line);
            String tag = new Tag().extract(line);
            return new AddPatientCommand(patientName, tag);
        }
        return null;
    }
}
