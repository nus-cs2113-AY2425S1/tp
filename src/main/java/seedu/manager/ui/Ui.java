package seedu.manager.ui;

import java.util.Scanner;

/**
 * Represents the program's user interface
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to EventManagerCLI.";
    private static final String COMMAND_PROMPT_MESSAGE = "Enter a command: ";

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

    /**
     * Gets input from the user, and prints that input back to the user
     */
    public void getCommand() {
        System.out.print(COMMAND_PROMPT_MESSAGE);
        String rawInput = this.userInput.nextLine();
        System.out.println(rawInput);
    }
}
