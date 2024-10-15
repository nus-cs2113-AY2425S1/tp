package seedu.duke.ui;
import java.util.Scanner;



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

    public void showMainScreen() {
        showLine();
        System.out.print("Meditask: ");
    }

    public void showTaskScreen(String patientName) {
        showLine();
        System.out.println("Patient: " + patientName);
        System.out.print("Input: ");

    }


    /**
     * Reads a command inputted by the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.hasNextLine() ? scanner.nextLine() : ""; //to prevent NoSuchElementException
    }

    /**
     * Displays a horizontal line to the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("welcome to Meditask");
        showLine();
    }

    /**
     * Closes the scanner
     */
    public void closeScanner() {
        scanner.close();
    }


}




