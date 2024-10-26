package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.HospitalCommand;
import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.state.StageManager;
import seedu.duke.parser.Parser;
import seedu.duke.storage.StorageFile;
import seedu.duke.ui.Ui;

public class MediTask {

    /** Version info of the program. */
    public static final String VERSION = "MediTask - Version 2.0";

    private Ui ui;
    private StorageFile storage;
    private Hospital hospital; // Load data from file
    private StageManager stageManager;

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        // ui.showGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     */
    private void start() {
        // try {
            ui = new Ui();
            storage = new StorageFile();
            hospital = storage.load(); // Load data from file
            stageManager = new StageManager();

            ui.showWelcome();

            HospitalCommand.setHospital(hospital);

        // }
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        String commandInput;


        do {
            // Show main screen if no patient is selected
            if (hospital.getSelectedPatient() != null) {
                ui.showTaskScreen(hospital.getSelectedPatient());
            } else {
                ui.showMainScreen();
            }

            commandInput = ui.readCommand();

            // parse and execute the command, and also pass in the currentState
            command = new Parser().parseCommand(commandInput, stageManager.getCurrentState());
            // CommandResult result = executeCommand(command, selectedPatient);


            CommandResult result = stageManager.runState(commandInput, command, hospital, ui);
            ui.showToUser(result.getFeedbackToUser());

            // Save hospital data to file
            storage.save(hospital);

        // TODO: Add a check for exit command
        } while (true);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new MediTask().run();
    }

}


// /**
//  * Executes the command and returns the result.
//  *
//  * @param command user command
//  * @return result of the command
//  */
// private CommandResult executeCommand(Command command, Patient selectedPatient) {
//     try {
//         command.setData(selectedPatient.getTaskList());
//         CommandResult result = command.execute();
//         return result;
//     } catch (Exception e) {
//         ui.showToUser(e.getMessage());
//         throw new RuntimeException(e);
//     }
// }


// public static void main(String[] args) {
//     Ui ui = new Ui();
//     StorageFile storage = new StorageFile();
//     Hospital hospital = storage.load(); // Load data from file

//     ui.showWelcome();

//     // Start in main state
//     State currentState = new State(StateType.MAIN_STATE);
//     assert currentState != null : "Current state should not be null.";

//     HospitalCommand.setHospital(hospital);

//     // Variable to hold the selected patient
//     Patient selectedPatient = null;

//     while (true) {
//         if (currentState.getState() == StateType.MAIN_STATE) {

//             ui.showMainScreen();
//             String commandInput = ui.readCommand();

//             // parse and execute the command, and also pass in the currentState
//             Parser parser = new Parser();
//             Command command = parser.parseCommand(commandInput, currentState);

//             if (command != null) {
//                 command.execute();

//                 // handle patient selection
//                 if (command instanceof seedu.duke.commands.SelectPatientCommand) {
//                     try {
//                         // convert user input to index
//                         int patientIndex = Integer.parseInt(commandInput.split(" ")[1]) - 1;
//                         // check if patient index fall within the number of patients in the hospital
//                         assert patientIndex >= 0 && patientIndex < hospital.getSize() : "Invalid patient index.";
//                         // get selected patient
//                         selectedPatient = hospital.getPatient(patientIndex);
//                     } catch (Exception e) {
//                         System.out.println("invalid patient selection.");
//                     }
//                 }
//             }
//         } else if (currentState.getState() == StateType.TASK_STATE) {
//             // show task screen for the selected patient
//             if (selectedPatient != null) {
//                 assert selectedPatient != null : "A patient must be selected in TASK_STATE.";
//                 // display selectedPatient name
//                 ui.showTaskScreen(selectedPatient.getName());
//             }
//             String commandInput = ui.readCommand();

//             // parse and execute commands in TASK_STATE
//             // pass the State object
//             Parser parser = new Parser();
//             Command command = parser.parseCommand(commandInput, currentState);

//             if (command != null) {
//                 command.setData(selectedPatient.getTaskList());
//                 CommandResult result = command.execute();
//                 ui.showToUser(result.getFeedbackToUser());
//             }
//         }
//         // Save hospital data to file
//         storage.save(hospital);
//     }
// }
