package seedu.main;

import java.util.List;
import java.util.Scanner;

/**
 * The class {@code UI} is responsible for handling user interactions with {@code Niwa} chatbot.
 * It provides methods for inputting commands and displaying messages.
 */
public class UI {
    private static final String PREFIX = "\t"; // Prefix for message formatting
    // Separator for message formatting
    private static final String SEPARATOR = "-------------------------------------";

    private static Scanner scanner; // Scanner for reading user input

    /**
     * Initializes a new instance of the {@code UI} class, setting up the scanner for user input.
     * There is only one scanner active during the session.
     */
    public UI() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    /**
     * Gets the input entered by the user.
     *
     * @return The input entered by the user as a string.
     */
    public String getUserInput() {
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
    public void printMessage(String message) {
        System.out.println(PREFIX + message);
    }

    /**
     * Prints a message to the console without a new line at the end.
     *
     * @param message The message to be printed.
     */
    public void printMiddleMessage(String message) {
        System.out.print(PREFIX + message);
    }

    /**
     * Prints multiple messages to the console, each as a separate line.
     *
     * @param messages The list of messages to print.
     */
    public void printMessages(List<String> messages) {
        messages.forEach(this::printMessage);
    }

    /**
     * Prints multiple messages to the console, each as a separate line.
     *
     * @param messages The messages to print, provided as a variable-length argument list.
     */
    public void printMessages(String... messages) {
        for (String m : messages) {
            printMessage(m);
        }
    }

    /**
     * Displays the result of a command execution
     *
     * @param results a list of Strings containing feedback.
     */
    public void showCommandResult(List<String> results) {
        if (results == null) {
            return;
        }
        printMessage(SEPARATOR); // Print a separator
        printMessages(results); // Print feedback to user
        printMessage(SEPARATOR); // Print another separator
    }
}
