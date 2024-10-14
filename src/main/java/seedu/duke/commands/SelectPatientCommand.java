package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;

public class SelectPatientCommand extends HospitalCommand {
    private int index;
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found in the list!";

    public SelectPatientCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            String patientName = hospital.getPatient(index).getName();
            String resultMessage = "selected Patient: " + patientName;
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        } catch (Hospital.PatientNotFoundException e) {
            String resultMessage = MESSAGE_PATIENT_NOT_FOUND;
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        }
    }
}