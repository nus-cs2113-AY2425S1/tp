package seedu.main;

import seedu.CategoryList;
import seedu.command.Command;
import seedu.command.HelpCommand;
import seedu.command.TestCommand;
import seedu.command.AddCategoryCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String NAME = "uNivUSaver";
    public static final String HI_MESSAGE = "Hello, %s is willing to help!";
    public static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command.";
    public static Scanner scanner; // Scanner for reading user input
    
    // Prefix for message formatting
    private static final String PREFIX = "\t";
    // Separator for message formatting
    private static final String SEPARATOR = "-------------------------------------";

    private static Parser parser; //Parser to parse the commands

    private static boolean isRunning = true;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        while (isRunning) {
            run();
        }
    }

    /**
     * Setter for the chatbot's running state.
     *
     * @param isRunning A boolean showing if the chatbot should continue running.
     */
    public void setRunning(boolean isRunning) {
        Main.isRunning = isRunning;
    }

    /**
     * Starts the chatbot and enters the command processing loop.
     */
    public static void run() {
        start();
        runCommandLoop();
    }

    /**
     * Starts the chatbot by printing the logo and greeting messages,
     * and sign up the Command objects.
     */
    public static void start() {
        printMessage(String.format(HI_MESSAGE, NAME));

        parser = new Parser();
        parser.registerCommands(new TestCommand());

        HelpCommand helpCommand = new HelpCommand();
        parser.registerCommands(helpCommand);

        AddCategoryCommand addCategoryCommand = new AddCategoryCommand(new CategoryList());
        parser.registerCommands(addCategoryCommand);

        // Set command list for the help command
        helpCommand.setCommands(new ArrayList<>(parser.getCommands().values()));
    }

    /**
     * Main command processing loop that retrieves user commands, processes, and displays the results.
     * The loop continues until the application is stopped.
     */
    private static void runCommandLoop() {
        while (isRunning) {
            String commandString = getUserInput();
            printMessage(commandString);

            Command command = parser.parseCommand(commandString);

            if (command == null){
                printMessage(INVALID_COMMAND_ERROR_MESSAGE);
                continue;
            }
            List<String> messages = command.execute();
            showCommandResult(messages);
        }
    }

    /**
     * Gets the input entered by the user.
     *
     * @return The input entered by the user as a string.
     */
    public static String getUserInput() {
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            // Silently consume all ignored lines (empty commands)
            while (input.trim().isEmpty()) {
                input = scanner.nextLine();
            }
            return input;
        }
        return "";
    }

    /**
     * Prints a single message to the console.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(PREFIX + message);
    }

    /**
     * Prints a message to the console without a new line at the end.
     *
     * @param message The message to be printed.
     */
    public static void printMiddleMessage(String message) {
        System.out.print(PREFIX + message);
    }

    /**
     * Prints multiple messages to the console, each as a separate line.
     *
     * @param messages The list of messages to print.
     */
    public static void printMessages(List<String> messages) {
        messages.forEach(Main::printMessage);
    }

    /**
     * Prints multiple messages to the console, each as a separate line.
     *
     * @param messages The messages to print, provided as a variable-length argument list.
     */
    public static void printMessages(String... messages) {
        for (String m : messages) {
            printMessage(m);
        }
    }

    /**
     * Displays the result of a command execution
     *
     * @param results a list of Strings containing feedback.
     */
    public static void showCommandResult(List<String> results) {
        if (results == null) {
            return;
        }
        printMessage(SEPARATOR); // Print a separator
        printMessages(results); // Print feedback to user
        printMessage(SEPARATOR); // Print another separator
    }
}
