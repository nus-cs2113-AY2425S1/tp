package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST_SUCCESS = "Here are the patients in your list!";
    public static final String MESSAGE_EMPTY_LIST = "The patient list is currently empty!";

    private static final Logger logger = Logger.getLogger(ListPatientCommand.class.getName());

    static {
        logger.setLevel(Level.SEVERE);
    }

    public ListPatientCommand() {
    }

    @Override
    public CommandResult execute() {
        assert hospital != null : "Hospital should not be null";

        if (hospital.getSize() == 0) {
            logger.log(Level.WARNING, "Attempted to list patients, but the list is empty.");
            System.out.println(MESSAGE_EMPTY_LIST);
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }

        hospital.printList();
        return new CommandResult(MESSAGE_LIST_SUCCESS);
    }
}
