package seedu.duke.commands;

import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to list all patients in the hospital system.
 * This command displays the list of patients along with their task completion rates.
 */
public class ListPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST_SUCCESS = "End of your patients list!";
    public static final String MESSAGE_EMPTY_LIST = "The patient list is currently empty!";

    private static final Logger logger = Logger.getLogger(ListPatientCommand.class.getName());


    Ui ui = new Ui();

    /**
     * Constructs a {@code ListPatientCommand}.
     */
    public ListPatientCommand() {
    }

    /**
     * Executes the command to list all patients in the hospital system.
     * If the patient list is empty, logs a warning and notifies the user.
     *
     * @return the result of the command, indicating success or that the list is empty.
     */
    @Override
    public CommandResult execute() {
        assert hospital != null : "Hospital should not be null";

        if (hospital.getSize() == 0) {
            logger.log(Level.WARNING, "Attempted to list patients, but the list is empty.");
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }

        ui.showPatientListWithCompletionRate(hospital);
        return new CommandResult(MESSAGE_LIST_SUCCESS);
    }
}
