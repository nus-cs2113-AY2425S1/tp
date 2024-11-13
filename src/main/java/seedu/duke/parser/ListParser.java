package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListPatientCommand;
import seedu.duke.commands.ListTaskCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
/**
 * Parses and executes the "list" command to display either patients or tasks based on the current application state.
 * Implements the {@link CommandParser} interface.
 */
public class ListParser implements CommandParser{
    /**
     * Executes the "list" command by returning the appropriate command to list patients or tasks
     * based on the current state of the application.
     *
     * @param line  The input string containing the "list" command (not utilized in execution).
     * @param state The current state of the application, used to determine whether to list patients or tasks.
     * @return A {@link ListPatientCommand} if in {@code MAIN_STATE},
     *         a {@link ListTaskCommand} if in {@code TASK_STATE},
     *         or {@code null} if the state is neither.
     */
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            return new ListPatientCommand();
        } else if (state.getState() == StateType.TASK_STATE) {
            return new ListTaskCommand();
        }

        return null;
    }
}
