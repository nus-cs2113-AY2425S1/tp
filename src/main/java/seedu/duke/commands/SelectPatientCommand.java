package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

public class SelectPatientCommand extends HospitalCommand {
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found in the list!";
    private State state; // to hold reference to the global state object

    private int index;

    public SelectPatientCommand(int index, State state) {
        this.index = index;
        this.state = state;  // store the global State object reference
    }
    @Override
    public CommandResult execute() {
        try {
            String patientName = hospital.getPatient(index).getName();
            String resultMessage = "selected Patient: " + patientName;

            // Change the global state to TASK_STATE
            state.setState(StateType.TASK_STATE);
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        } catch (Hospital.PatientNotFoundException e) {
            String resultMessage = MESSAGE_PATIENT_NOT_FOUND;
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        }
    }
}
