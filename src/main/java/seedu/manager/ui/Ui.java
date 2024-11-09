package seedu.manager.ui;

import seedu.manager.command.Command;

import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

//@@author jemehgoh
/**
 * Represents the program's user interface
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to EventManagerCLI.";
    private static final String COMMAND_PROMPT_MESSAGE = "Enter a command: ";
    private static final String SEPARATOR = "------------------------";

    private final Scanner userInput;
    private final PrintStream utf8;

    /**
     * Constructs a new Ui
     */
    public Ui() {
        this.userInput = new Scanner(System.in);
        this.utf8 = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    /**
     * Greets user upon program startup
     */
    public void greetUser() {
        utf8.println(WELCOME_MESSAGE);
    }

    /**
     * Gets input from the user, and executes commands based on that input
     */
    public String getCommand() {
        utf8.print(COMMAND_PROMPT_MESSAGE);
        return this.userInput.nextLine();
    }

    //@@author MatchaRRR
    /**
     * Shows the output message of a command to the users.
     */
    public void showOutputToUser(Command command) {
        utf8.println(command.getMessage());
        utf8.println(SEPARATOR);
    }

    //@@author jemehgoh
    /**
     * Shows the error message of an exception to the user.
     */
    public void showErrorMessageToUser(Exception exception) {
        utf8.println(exception.getMessage());
        utf8.println(SEPARATOR);
    }

    //@@author KuanHsienn
    /**
     * Shows a message to the user.
     *
     * @param message The message to display to the user.
     */
    public void showMessage(String message) {
        utf8.println(message);
        utf8.println(SEPARATOR);
    }
}
