package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST_SUCCESS = "End of your patients list!";
    public static final String MESSAGE_EMPTY_LIST = "The patient list is currently empty!";

    private static final Logger logger = Logger.getLogger(ListPatientCommand.class.getName());

    static {
        logger.setLevel(Level.SEVERE);
    }

    public ListPatientCommand() {
    }

    Ui ui = new Ui();

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
