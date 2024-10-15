package seedu.duke;

import seedu.duke.commands.HospitalCommand;
import seedu.duke.data.hospital.Hospital;
import seedu.duke.ui.Ui;
import seedu.duke.parser.Parser;
import seedu.duke.data.hospital.Patient;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.commands.Command;
import seedu.duke.commands.SelectPatientCommand;


public class MediTask {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        Hospital hospital = new Hospital();
        ui.showWelcome();
        State currentState = new State(StateType.MAIN_STATE.ordinal()); // Start in MAIN_STATE
        HospitalCommand.setHospital(hospital);

        // variable to hold the selected patient
        Patient selectedPatient = null;

        while (true) {
            if (currentState.getState() == StateType.MAIN_STATE) {

                ui.showMainScreen();
                String commandInput = ui.readCommand();

                // parse and execute the command, and also pass in  the currentState
                Parser parser = new Parser(commandInput, currentState);
                Command command = parser.parseCommand();

                if (command != null) {
                    command.execute();

                    //handle patient selection
                    if (command instanceof seedu.duke.commands.SelectPatientCommand) {
                        try {
                            int patientIndex = Integer.parseInt(commandInput.split(" ")[1]); // convert user input to index
                            selectedPatient = hospital.getPatient(patientIndex); // Get selected patient
                        } catch (Exception e) {
                            System.out.println("invalid patient selection.");
                        }
                    }
                }
            } else if (currentState.getState() == StateType.TASK_STATE) {
                //show task screen for the selected patient
                if (selectedPatient != null) {
                    ui.showTaskScreen(selectedPatient.getName()); //display selectedPatient name
                }
                String commandInput = ui.readCommand();

                //parse and execute commands in TASK_STATE
                Parser parser = new Parser(commandInput, currentState); //pass the State object
                Command command = parser.parseCommand();

                if (command != null) {
                    command.execute();

                }
            }
        }
    }
}
