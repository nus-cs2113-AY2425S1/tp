package seedu.duke.data.state;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.exception.InvalidCommandException;
import seedu.duke.data.hospital.Hospital;
import seedu.duke.ui.Ui;

public class StageManager {
    private State currentState;

    public StageManager() {
        this.currentState = new State();
        assert currentState != null : "Current state should not be null.";
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void changeState(StateType state) {
        currentState.setState(state);
    }

    public StateType getState() {
        return currentState.getState();
    }

    public CommandResult runState(String commandInput, Command command, Hospital hospital, Ui ui) {

        try {
            switch (currentState.getState()) {
                case MAIN_STATE:

                    return runMainState(commandInput, command, hospital);

                case TASK_STATE:
                    // ui.showTaskScreen(hospital.getSelectedPatient());
                    return runTaskState(commandInput, command, hospital);

                default:
                    return null;
            }
        } catch (InvalidCommandException e) {
            return new CommandResult(e.getMessage());
        }
    }

    public CommandResult runMainState(String commandInput, Command command, Hospital hospital)
            throws InvalidCommandException {
        if (command == null) {
            throw new InvalidCommandException("Invalid Patient Commands.");
        }
        // handle patient selection
        if (command instanceof seedu.duke.commands.SelectPatientCommand) {
            try {
                // convert user input to index
                int patientIndex = Integer.parseInt(commandInput.split(" ")[1]) - 1;
                // check if patient index fall within the number of patients in the hospital
                assert patientIndex >= 0 && patientIndex < hospital.getSize() : "Invalid patient index.";
                // get selected patient
                // selectedPatient = hospital.getPatient(patientIndex);
                hospital.setSelectedPatient(patientIndex);

            } catch (Exception e) {
                System.out.println("Invalid patient selection.");
            }
        }
        return command.execute();
    }

    public CommandResult runTaskState(String commandInput, Command command, Hospital hospital)
            throws InvalidCommandException {
        if (command == null) {
            throw new InvalidCommandException("Invalid task command.");
        }
        // show task screen for the selected patient
        if (hospital.getSelectedPatient() == null) {
            throw new InvalidCommandException("No patient selected.");
        }

        assert hospital.getSelectedPatient() != null : "A patient must be selected in TASK_STATE.";
        // display selectedPatient name
        // ui.showTaskScreen(selectedPatient.getName());

        command.setData(hospital.getSelectedPatient().getTaskList());
        return command.execute();
    }

}
