package seedu.duke.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.hospital.Patient;

public class FindPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Here are the matching patients in your list: \n%1$s";
    public static final String MESSAGE_NO_MATCH = "There are no matching patients in your list.";

    private static final Logger logger = Logger.getLogger(FindPatientCommand.class.getName());

    private final String keyword;

    static {
        logger.setLevel(Level.SEVERE);
    }

    public FindPatientCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        assert keyword != null : "Keyword should not be null";

        // Find matching patients
        ArrayList<Patient> foundPatients = hospital.findPatients(keyword);

        // Check if any patients were found
        if (foundPatients.isEmpty()) {
            logger.log(Level.SEVERE, "No matching patients found: {0}", keyword);
            return new CommandResult(MESSAGE_NO_MATCH);
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, foundListToString(foundPatients)));
        }
    }




    /**
     * Converts a list of found patients into a formatted string.
     *
     * @param foundPatients The list of patients to format.
     * @return A string representation of the found patients.
     */
    private String foundListToString(ArrayList<Patient> foundPatients) {
        StringBuilder foundList = new StringBuilder();
        for (int i = 0; i < foundPatients.size(); i++) {
            foundList.append(i + 1).append(". ").append(foundPatients.get(i).getName()).append("\n");
        }
        return foundList.toString();
    }
}
