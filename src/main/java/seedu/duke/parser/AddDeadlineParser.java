package seedu.duke.parser;

import seedu.duke.commands.AddTaskCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.exception.DateParseException;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.parserutils.DateFormat;
import seedu.duke.parser.parserutils.Duration;
import seedu.duke.parser.parserutils.TaskName;
import seedu.duke.parser.parserutils.Tag;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDeadlineParser implements CommandParser {

    private static final Logger LOGGER = Logger.getLogger(AddDeadlineParser.class.getName());

    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.TASK_STATE) {
            String taskName = new TaskName().extract(line);
            String by = new Duration().extract(line);
            String tag = new Tag().extract(line);
            try {
                // Parse, validate, and format the date using DateFormat
                by = DateFormat.validateAndParseToStandardFormat(by);
            } catch (DateParseException e) {
                LOGGER.log(Level.SEVERE, "Invalid date/time format for deadline: {0}", by);
                return null;
            }
            return new AddTaskCommand("deadline", taskName, by, tag);
        }
        return null;
    }
}
