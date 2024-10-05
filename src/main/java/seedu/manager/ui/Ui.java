package seedu.manager.ui;

import java.util.Scanner;

/**
 * Represents the program's user interface
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to EventManagerCLI.";

    private final Scanner userInput;

    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    /**
     * Greets user upon program startup
     */
    public void greetUser() {
        System.out.println(WELCOME_MESSAGE);
    }
}
