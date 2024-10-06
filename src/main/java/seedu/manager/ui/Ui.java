package seedu.manager.ui;

import seedu.manager.command.Command;
import seedu.manager.command.CommandOutput;
import seedu.manager.parser.Parser;

import java.util.Scanner;

/**
 * Represents the program's user interface
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to EventManagerCLI.";
    private static final String COMMAND_PROMPT_MESSAGE = "Enter a command: ";
    private static final String GOODBYE_MESSAGE = "Thank you for using EventManagerCLI. Goodbye!";

    private final Scanner userInput;
    private final Parser parser;

    /**
     * Constructs a new Ui
     */
    public Ui() {
        this.userInput = new Scanner(System.in);
        this.parser = new Parser();
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
        boolean isGettingCommands = true;

        while (isGettingCommands) {
            System.out.print(COMMAND_PROMPT_MESSAGE);
            String rawInput = this.userInput.nextLine();
            Command command = parser.parseCommand(rawInput);
            CommandOutput output = command.execute();
            output.printMessage();
            isGettingCommands = !output.getCanExit();
        }
    }
}
