package seedu.duke.ui;

import java.util.List;
import java.util.Scanner;

import seedu.duke.common.Messages;
import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.hospital.Patient;
import seedu.duke.data.task.TaskList;

/**
 * The Ui class handles the user interface for the task management application.
 * It provides methods for displaying messages and interacting with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main screen for the task management application.
     *
     * @param hospital The hospital object to display the main screen for.
     */
    public void showMainScreen(Hospital hospital) {
        showLine();
        showCompletionRate(hospital);
        System.out.print(Messages.MESSAGE_MAIN_PROMPT);
    }

    /**
     * Displays the task screen for a patient.
     *
     * @param patient The patient object to display the task screen for.
     */
    public void showTaskScreen(Patient patient) {
        showLine();
        System.out.println("Patient: " + patient.getName() + patient.getFormattedTag());
        System.out.print(Messages.MESSAGE_MAIN_PROMPT);
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showToUser(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showToUserException(String message) {
        System.out.println(Colors.ANSI_RED + message + Colors.ANSI_RESET);
    }

    // @@kennethszj
    /**
     * Displays the list of tasks for a patient.
     *
     * @param patient The patient object to display the tasks from.
     */
    public void showTaskList(Patient patient) {
        showLine();
        // retrieve and display completion rate
        TaskList taskList = patient.getTaskList();
        String completionPercentage = taskList.completionRatePercentageToString();
        // display info with completion rate
        System.out.printf("%sPatient:%s %s %s [Tasks Completed: %s%s%s]%n",
                Colors.ANSI_BLUE, Colors.ANSI_RESET,
                patient.getName(), patient.getFormattedTag(),
                Colors.ANSI_GREEN, completionPercentage, Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_BLUE + "Here are the tasks in your list!" + Colors.ANSI_RESET);
        System.out.println(taskList); // use TaskList's toString method to print the list of tasks
    }

    // @@kennethszj
    /**
     * Displays the list of patients in the hospital with their completion rates.
     *
     * @param hospital The hospital object to display the patients from.
     */
    public void showPatientListWithCompletionRate(Hospital hospital) {
        showLine();

        // calculate and display the overall task completion rate
        showCompletionRate(hospital);
        // display each patient
        System.out.println(Colors.ANSI_BLUE + "Here are the patients in your list:" + Colors.ANSI_RESET);
        List<Patient> patients = hospital.getPatients();
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);

            // retrieve and display completion rate for each patient
            TaskList taskList = patient.getTaskList();
            String completionPercentage = taskList.completionRatePercentageToString();

            System.out.printf("%s%d.%s %s %s [Tasks Completed: %s] %n",
                    Colors.ANSI_BLUE, i + 1, Colors.ANSI_RESET,
                    patient.getName(), patient.getFormattedTag(), completionPercentage);
        }
    }

    // @@kennethszj
    /**
     * Displays the completion rate of the hospital.
     *
     * @param hospital The hospital object to calculate the completion rate from.
     */
    public void showCompletionRate(Hospital hospital) {
        // Calculate and display the overall task completion rate
        double completionRate = hospital.calculateOverallCompletionRate();
        System.out.printf("%s%.0f%% of all tasks are completed.%s%n", Colors.ANSI_GREEN, completionRate,
                Colors.ANSI_RESET);
    }

    /**
     * Reads a command inputted by the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        assert scanner != null;
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Displays a horizontal line to the console.
     */
    public void showLine() {
        System.out.println(Messages.MESSAGE_BREAKLINE);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(Messages.LOGO);
        System.out.println(Messages.MESSAGE_WELCOME);
    }

    /**
     * Closes the scanner
     */
    public void closeScanner() {
        scanner.close();
    }

}
