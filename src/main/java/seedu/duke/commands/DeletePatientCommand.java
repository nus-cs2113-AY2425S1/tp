package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeletePatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Deleted patient: %1$s";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found in the list!";

    private static final Logger logger = Logger.getLogger(DeletePatientCommand.class.getName());

    private int index;

    static {
        logger.setLevel(Level.SEVERE);
    }

    public DeletePatientCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResult execute() {
        assert index >= 0 : "Index should be non-negative";

        try {
            String patientName = hospital.getPatient(index).getName();
            hospital.deletePatient(index);
            String resultMessage = String.format(MESSAGE_SUCCESS, patientName);
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        } catch (Hospital.PatientNotFoundException e) {
            logger.log(Level.SEVERE, "Attempted to delete a patient at an invalid index: {0}", index);
            System.out.println(MESSAGE_PATIENT_NOT_FOUND);
            return new CommandResult(MESSAGE_PATIENT_NOT_FOUND);
        }
    }
}
