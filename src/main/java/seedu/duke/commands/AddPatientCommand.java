package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to add a patient to the hospital system.
 * This command checks for duplicates before adding a patient to the hospital.
 */
public class AddPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the hospital";

    private static final Logger logger = Logger.getLogger(AddPatientCommand.class.getName());

    private String name;
    private String tag;



    /**
     * Constructs an {@code AddPatientCommand} with the specified patient name and optional tag.
     *
     * @param name the name of the patient to be added.
     * @param tag the optional tag for the patient. Can be null or empty if no tag is provided.
     */
    public AddPatientCommand(String name, String tag) {
        this.name = name;
        this.tag = tag; // Can be null if no tag is provided
    }

    /**
     * Executes the command to add a patient to the hospital.
     * Checks for duplicate patients, logs errors if necessary, and adds the patient if they do not exist.
     *
     * @return the result of the command, indicating success or failure due to duplication.
     */
    @Override
    public CommandResult execute() {
        assert name != null && !name.isEmpty() : "Patient name should not be null or empty";

        if (hospital.isDuplicatePatient(name)) {
            logger.log(Level.WARNING, "Duplicate patient detected: {0}", name);
            return new CommandResult(MESSAGE_DUPLICATE_PATIENT);
        }

        hospital.addPatient(name, tag);
        String resultMessage;
        if (tag != null && !tag.isEmpty()) {
            resultMessage = String.format(MESSAGE_SUCCESS, name + " [" + tag + "]");
        } else {
            resultMessage = String.format(MESSAGE_SUCCESS, name);
        }
        // System.out.println(resultMessage);

        return new CommandResult(resultMessage);
    }

}
