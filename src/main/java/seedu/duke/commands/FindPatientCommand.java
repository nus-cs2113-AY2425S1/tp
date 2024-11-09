package seedu.duke.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.hospital.Patient;

/**
 * Represents a command to find patients in the hospital system by a specified keyword.
 * This command searches for patients whose names contain the keyword.
 */
public class FindPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Here are the matching patients in your list: \n%1$s";
    public static final String MESSAGE_NO_MATCH = "There are no matching patients in your list.";

    private static final Logger logger = Logger.getLogger(FindPatientCommand.class.getName());

    private final String keyword;


    /**
     * Constructs a {@code FindPatientCommand} with the specified keyword to search for.
     *
     * @param keyword the keyword to match against patient names.
     */
    public FindPatientCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find patients matching the keyword in the hospital system.
     * Logs if no matching patients are found.
     *
     * @return the result of the command, either listing matching patients or indicating no matches.
     */
    @Override
    public CommandResult execute() {
        assert keyword != null : "Keyword should not be null";

        // Find matching patients
        ArrayList<Patient> foundPatients = hospital.findPatients(keyword);

        // Check if any patients were found
        if (foundPatients.isEmpty()) {
            logger.log(Level.WARNING, "No matching patients found: {0}", keyword);
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
            foundList.append(i + 1).append(". ").append(foundPatients.get(i).getName())
                    .append(foundPatients.get(i).getFormattedTag())
                    .append("\n");
        }
        return foundList.toString();
    }
}
