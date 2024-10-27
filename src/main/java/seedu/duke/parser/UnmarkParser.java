package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.UnmarkTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.parserUtils.Index;

import static java.lang.Integer.parseInt;

public class UnmarkParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if(state.getState() == StateType.TASK_STATE){
            int id = parseInt(new Index().extract(line));
            return new UnmarkTaskCommand(id);
        }
        return null;
    }
}
