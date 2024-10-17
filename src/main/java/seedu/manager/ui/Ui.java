package seedu.manager.ui;

import seedu.manager.command.Command;

import java.util.Scanner;

/**
 * Represents the program's user interface
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to EventManagerCLI.";
    private static final String COMMAND_PROMPT_MESSAGE = "Enter a command: ";
    private static final String SEPARATOR = "------------------------";

    private final Scanner userInput;

    /**
     * Constructs a new Ui
     */
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
     * Gets input from the user, and executes commands based on that input
     */
    public String getCommand() {
        System.out.print(COMMAND_PROMPT_MESSAGE);
        return this.userInput.nextLine();
    }

    /**
     * show the output message a of command to the users.
     */
    public void showOutputToUser(Command command){
        System.out.println(command.getMessage());
        System.out.println(SEPARATOR);
    }
}
