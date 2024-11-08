package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeletePatientCommand;
import seedu.duke.commands.DeleteTaskCommand;
import seedu.duke.data.exception.IllegalValueException;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.parserutils.Index;

/**
 * Parses and executes the "delete" command to remove a patient or task from the application.
 * Implements the {@link CommandParser} interface.
 */
public class DeleteParser implements CommandParser {
    public static final String MESSAGE_INVALID_INDEX = "Invalid input: please enter a valid number for the index.";

    /**
     * Executes the "delete" command by extracting the index from the input line and determining
     * whether to delete a patient or a task based on the current state.
     *
     * @param line  The input string containing the "delete" command followed by the index of the item to be deleted.
     * @param state The current state of the application,
     *              used to determine whether the command relates to a patient or a task.
     * @return A {@link DeletePatientCommand} if in {@code MAIN_STATE},
     *      or a {@link DeleteTaskCommand} if in {@code TASK_STATE}.
     * @throws IllegalValueException if the index provided is not a valid integer.
     */
    @Override
    public Command execute(String line, State state) throws IllegalValueException {
        try {
            int id = Integer.parseInt(new Index().extract(line));

            if (state.getState() == StateType.MAIN_STATE) {
                return new DeletePatientCommand(id);
            } else if (state.getState() == StateType.TASK_STATE) {
                return new DeleteTaskCommand(id);
            } else {
                throw new IllegalValueException("Invalid state for delete command.");
            }
        } catch (NumberFormatException e) {
            // Throw IllegalValueException if the index is not a valid integer
            throw new IllegalValueException(MESSAGE_INVALID_INDEX);
        }
    }
}
