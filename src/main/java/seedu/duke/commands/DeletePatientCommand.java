package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;

public class DeletePatientCommand extends HospitalCommand {
    private int index;
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found in the list!";

    public DeletePatientCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            String patientName = hospital.getPatient(index).getName();
            hospital.deletePatient(index);
            String resultMessage = "deleted Patient: " + patientName;
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        } catch (Hospital.PatientNotFoundException e) {
            String resultMessage = MESSAGE_PATIENT_NOT_FOUND;
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        }
    }
}

