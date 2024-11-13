package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.hospital.Hospital;

/**
 * Represents a command to delete a patient from the hospital system.
 * This command locates a patient by their index and removes them from the list if they exist.
 */
public class DeletePatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Deleted patient: %1$s";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found in the list!";

    private static final Logger logger = Logger.getLogger(DeletePatientCommand.class.getName());

    private int index;

    /**
     * Constructs a {@code DeletePatientCommand} with the specified index of the patient to delete.
     * The index is adjusted to be zero-based for internal list access.
     *
     * @param index the one-based index of the patient in the list to delete.
     */
    public DeletePatientCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the command to delete a patient from the hospital.
     * Retrieves the patient's name before deletion for confirmation and
     * handles any exception if the index is invalid.
     *
     * @return the result of the command, indicating success or failure due to an invalid index.
     */
    @Override
    public CommandResult execute() {
        assert index >= 0 : "Index should be non-negative";

        try {
            String patientName = hospital.getPatient(index).getName();
            hospital.deletePatient(index);
            String resultMessage = String.format(MESSAGE_SUCCESS, patientName);
            // System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        } catch (Hospital.PatientNotFoundException e) {
            logger.log(Level.WARNING, "Attempted to delete a patient at an invalid index: {0}", index);
            // System.out.println(MESSAGE_PATIENT_NOT_FOUND);
            return new CommandResult(MESSAGE_PATIENT_NOT_FOUND);
        }
    }
}
